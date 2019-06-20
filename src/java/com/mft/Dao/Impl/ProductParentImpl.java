/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao.Impl;

import com.mft.Bean.ProductParent;
import com.mft.Dao.ProductParetnDao;
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
public class ProductParentImpl implements ProductParetnDao {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Override
    public List<ProductParent> getAllPs() {
        // TODO Auto-generated method stub
        List<ProductParent> ps = null;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select * from productParent";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            ps = new ArrayList<ProductParent>();
            while (rs.next()) {
                ProductParent p = new ProductParent(rs.getInt(1), rs.getString(2));
                ps.add(p);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return ps;
    }

    @Override
    public int addPp(String name) {
        // TODO Auto-generated method stub
        String sql = "insert into productParent values('" + name + "')";
        int r = JDBCUtil.executeUpdate(sql, null);
        return r;
    }

    @Override
    public int delPp(int id) {
        // TODO Auto-generated method stub
        String sql = "delete from productParent where id=" + id;
        int r = JDBCUtil.executeUpdate(sql, null);
        return r;
    }

    @Override
    public int upPp(int id, String name) {
        // TODO Auto-generated method stub
        String sql = "update productParent set name='" + name + "' where id=" + id;
        int r = JDBCUtil.executeUpdate(sql, null);
        return r;
    }

    @Override
    public boolean isPp(String name) {
        // TODO Auto-generated method stub
        boolean b = false;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select count(*) from productParent where name='" + name + "'";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            int r = 0;
            if (rs.next()) {
                r = rs.getInt(1);
            }
            if (r > 0) {
                b = true;
            } else {
                b = false;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return b;
    }
}
