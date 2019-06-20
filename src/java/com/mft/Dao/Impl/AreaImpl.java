/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao.Impl;

import com.mft.Bean.Area;
import com.mft.Dao.AreaDao;
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
public class AreaImpl implements AreaDao {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Override
    public List<Area> getAreas(int cid, int pid) {
        // TODO Auto-generated method stub
        List<Area> as = null;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select * from area where cid=" + cid + " and pid=" + pid;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            as = new ArrayList<Area>();
            while (rs.next()) {
                Area a = new Area(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
                as.add(a);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return as;
    }

    @Override
    public List<Area> getAreas(int pid) {
        // TODO Auto-generated method stub
        List<Area> as = null;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select * from area where pid=" + pid;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            as = new ArrayList<Area>();
            while (rs.next()) {
                Area a = new Area(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
                as.add(a);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return as;
    }

}
