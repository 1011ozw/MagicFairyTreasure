/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao.Impl;

import com.mft.Bean.OrderDetail;
import com.mft.Dao.OrderDetailDao;
import com.mft.Util.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 张帆
 */
public class OrderDetailImpl implements OrderDetailDao {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Override
    public int addOrderDetail(OrderDetail o) {
        // TODO Auto-generated method stub
        String sql = "insert into orderDetail values(" + o.getOid() + "," + o.getPid() + "," + o.getQuantity() + ",'" + o.getCost() + "')";
        int r = JDBCUtil.executeUpdate(sql, null);
        return r;
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOid(int oid) {
        // TODO Auto-generated method stub
        List<OrderDetail> os = null;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select orderDetail.id, orderDetail.oid,orderDetail.pid,orderDetail.quantity,orderDetail.cost,product.image,product.name,product.price  from orderDetail,product where orderDetail.pid=product.id and oid=" + oid;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            os = new ArrayList<OrderDetail>();
            while (rs.next()) {
                OrderDetail o = new OrderDetail(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
                o.setImage(rs.getString(6));
                o.setPname(rs.getString(7));
                o.setPrice(rs.getString(8));
                os.add(o);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return os;
    }

    @Override
    public int delOrderDetailByOid(int oid) {
        // TODO Auto-generated method stub
        int r = 0;
        try {
            conn = JDBCUtil.getconn();
            String sql = "delete from orderDetail where oid=" + oid;
            pstmt = conn.prepareStatement(sql);
            r = pstmt.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return r;
    }
}
