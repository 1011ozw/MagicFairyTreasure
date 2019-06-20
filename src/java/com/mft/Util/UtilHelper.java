/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Util;

import com.mft.Bean.OrderDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author 张帆
 */
public class UtilHelper {

    /**
     * 帮助类
     * @param dbName
     * @return 
     */
    //得到要查表信息的总数
    public static int getCount(String dbName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select count(*) from [" + dbName + "]";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return count;
    }
    //获取总页数

    public static int totalPages(int pageSize, int countPro) {//参数说明:1每页显示个数  2总个数
        int totalPages = 0;
        if (countPro % pageSize == 0) {
            totalPages = countPro / pageSize;
        } else {
            totalPages = countPro / pageSize + 1;
        }
        return totalPages;
    }
    //获得当前时间

    public static String getNowTime() {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = sf.format(date);
        return time;
    }

    public static OrderDetail showSumOrder(int oid) {
        //TODO:获取订单的商品数量
        Connection con = null;
        PreparedStatement stim = null;
        ResultSet rs = null;
        OrderDetail od = null;
        try {
            con = JDBCUtil.getconn();
            String sql = "select quantity,pid from orderDetail where oid = " + oid;
            stim = con.prepareStatement(sql);
            rs = stim.executeQuery();
            while (rs.next()) {
                od = new OrderDetail(0, 0, rs.getInt(2), rs.getInt(1), null);
                od.setQuantity(rs.getInt(1));
                od.setPid(rs.getInt(2));
            }
        } catch (Exception e) {
            // TODO: 异常处理
            e.printStackTrace();
        } finally {
            JDBCUtil.release(con, stim, rs);
        }
        return od;
    }
}
