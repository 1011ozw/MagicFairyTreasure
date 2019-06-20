/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao.Impl;

import com.mft.Bean.ProductCategory;
import com.mft.Dao.ProductCategoryDao;
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
public class ProductCategoryImpl implements ProductCategoryDao {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Override
    public List<ProductCategory> getPcsByPid(int pid) {
        // TODO Auto-generated method stub
        List<ProductCategory> ps = null;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select * from productCategory where parentId=" + pid;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            ps = new ArrayList<ProductCategory>();
            while (rs.next()) {
                ProductCategory p = new ProductCategory(rs.getInt(1), rs.getString(2), rs.getInt(3));
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
    public String getPcName(int pcid, int pid) {
        // TODO Auto-generated method stub
        String str = "";
        try {
            conn = JDBCUtil.getconn();
            String sql = "select name from productCategory where parentId=" + pid + " and pcid=" + pcid;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                str = rs.getString(1);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return str;
    }

    @Override
    public int addPc(int ppid, String name) {
        // TODO Auto-generated method stub
        String sql = "insert into productCategory values('" + name + "'," + ppid + ")";
        int r = JDBCUtil.executeUpdate(sql, null);
        return r;
    }

    @Override
    public int delPc(int ppid, int pcid) {
        // TODO Auto-generated method stub
        String sql = "delete from productCategory where parentid=" + ppid + " and id=" + pcid;
        int r = JDBCUtil.executeUpdate(sql, null);
        return r;
    }

    @Override
    public boolean isPc(int ppid, String name) {
        // TODO Auto-generated method stub
        boolean b = false;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select count(*) from productCategory where parentid=" + ppid + " and name='" + name + "'";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int r = rs.getInt(1);
                if (r > 0) {
                    b = true;
                } else {
                    b = false;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return b;
    }

    @Override
    public int upPc(int ppid, int pcid, String name) {
        // TODO Auto-generated method stub
        String sql = "update productCategory set name='" + name + "' where parentid=" + ppid + " and id=" + pcid;
        int r = JDBCUtil.executeUpdate(sql, null);
        return r;
    }

    @Override
    public int delPcs(int ppid) {
        // TODO Auto-generated method stub
        String sql = "delete from productCategory where parentid=" + ppid;
        int r = JDBCUtil.executeUpdate(sql, null);
        return r;
    }
}
