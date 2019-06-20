/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao.Impl;

import com.mft.Bean.City;
import com.mft.Dao.CityDao;
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
public class CityImpl implements CityDao {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     *
     * @param pid
     * @return
     */
    @Override
    public List<City> getCitysByPid(int pid) {
        // TODO Auto-generated method stub
        List<City> cs = null;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select * from city where pid=" + pid;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            cs = new ArrayList<City>();
            while (rs.next()) {
                City c = new City(rs.getInt(1), rs.getString(2), rs.getInt(3));
                cs.add(c);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return cs;
    }
}
