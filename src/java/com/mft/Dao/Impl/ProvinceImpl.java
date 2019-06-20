/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao.Impl;

import com.mft.Bean.Province;
import com.mft.Dao.ProvinceDao;
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
public class ProvinceImpl implements ProvinceDao {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Override
    public List<Province> getProvinces() {
        // TODO Auto-generated method stub
        List<Province> ps = null;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select * from province";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            ps = new ArrayList<Province>();
            while (rs.next()) {
                Province p = new Province(rs.getInt(1), rs.getString(2));
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
}
