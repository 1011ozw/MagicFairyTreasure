/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author 张帆
 */
public class idusHelper {

    public static Connection con = null;
    public static PreparedStatement stim = null;
    public static ResultSet rs = null;

    public static int idu(String sql) {
        //TODO 增,删,改通用方法。
        int r = -1;
        try {
            con = JDBCUtil.getconn();
            stim = con.prepareStatement(sql);
            r = stim.executeUpdate();
        } catch (Exception e) {
            // TODO: 异常处理
            e.printStackTrace();
        } finally {
            JDBCUtil.release(con, stim, rs);
        }
        return r;
    }
}
