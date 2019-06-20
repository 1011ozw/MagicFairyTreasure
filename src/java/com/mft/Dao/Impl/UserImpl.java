/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao.Impl;

import com.mft.Bean.User;
import com.mft.Dao.UserDao;
import com.mft.Util.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 张帆
 */
public class UserImpl implements UserDao {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Override
    public int addUser(User u) {
        // TODO Auto-generated method stub
        String sql = "insert into [user] (username,password,status)values('" + u.getUsername() + "','" + u.getPassword() + "',1)";
        int r = JDBCUtil.executeUpdate(sql, null);
        return r;
    }

    @Override
    public User checkLogin(String name, String pwd) {
        // TODO Auto-generated method stub
        User u = null;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select * from [user] where username=? and password=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, pwd);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(12));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return u;
    }

    public boolean isUserByName(String name) {
        // TODO Auto-generated method stub
        boolean b = false;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select * from [user] where username='" + name + "'";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                b = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return b;
    }

    Connection con = null;
    PreparedStatement stim = null;

    @Override
    public User login(String userName, String userPwd, int type) {
        // TODO 用户登录
        User us = null; //返回值
        try {
            con = JDBCUtil.getconn();
            String sql = "select * from [user] where"
                    + " username = ? and [password] = ? and [status] = ? ";
            stim = con.prepareStatement(sql);
            stim.setString(1, userName);
            stim.setString(2, userPwd);
            stim.setInt(3, type);
            rs = stim.executeQuery();
            while (rs.next()) {
                us = new User();
                us.setId(rs.getInt(1));
                us.setUsername(rs.getString(2));
                us.setPassword(rs.getString(3));
                us.setSex(rs.getString(4));
                us.setBirthday(rs.getString(5));
                us.setIdentitycard(rs.getString(6));
                us.setEmail(rs.getString(7));
                us.setMobile(rs.getString(8));
                us.setAddress(rs.getString(9));
                us.setStatus(rs.getInt(10));
                us.setImage(rs.getString(11));
                us.setBalance(rs.getString(12));
            }

        } catch (Exception e) {
            // TODO: 异常处理
            e.printStackTrace();
        } finally {
            JDBCUtil.release(con, stim, rs);
        }
        return us;
    }

    public List<User> userId(int pageIndex, int pageSum) {
        // TODO 查询所有用户或者ID
        List<User> ids = null;
        try {
            con = JDBCUtil.getconn();
            String sql = "select * from [user]";
            if (pageSum != 1 && pageSum != 0) {
                sql = "select top " + pageSum + " * from [user] where id not in(select top " + pageIndex * pageSum + " id from [user])";
            }
            System.out.println(sql + "||" + pageSum);
            stim = con.prepareStatement(sql);
            rs = stim.executeQuery();
            ids = new ArrayList<User>();
            while (rs.next()) {
                System.out.println("1---1");
                User u = new User();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setSex(rs.getString(4));
                u.setBirthday(rs.getString(5));
                u.setIdentitycard(rs.getString(6));
                u.setEmail(rs.getString(7));
                u.setMobile(rs.getString(8));
                u.setAddress(rs.getString(9));
                u.setStatus(rs.getInt(10));
                u.setImage(rs.getString(11));
                u.setBalance(rs.getString(12));
                ids.add(u);
            }
        } catch (Exception e) {
            // TODO: 异常处理
            e.printStackTrace();
        } finally {
            JDBCUtil.release(con, stim, rs);
        }
        return ids;
    }

    @Override
    public User user(int userId) {
        // TODO 根据用户ID查询用户信息
        User u = null;
        try {
            con = JDBCUtil.getconn();
            String sql = "select * from [user] where id=" + userId;
            stim = con.prepareStatement(sql);
            rs = stim.executeQuery();
            u = new User();
            while (rs.next()) {
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setAddress(rs.getString(9));
            }
        } catch (Exception e) {
            // TODO:异常处理
            e.printStackTrace();
        } finally {
            JDBCUtil.release(con, stim, rs);
        }
        return u;
    }

    @Override
    public User bynameuser(String username) {
        User user = null;
        String sql = "select * from [user] where username=?";
        try {
            con = JDBCUtil.getconn();
        } catch (SQLException ex) {
            Logger.getLogger(UserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            stim = con.prepareStatement(sql);
            stim.setString(1, username);
            rs = stim.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setSex(rs.getString(4));
                user.setBirthday(rs.getString(5));
                user.setIdentitycard(rs.getString(6));
                user.setEmail(rs.getString(7));
                user.setMobile(rs.getString(8));
                user.setAddress(rs.getString(9));
                user.setStatus(rs.getInt(10));
                user.setImage(rs.getString(11));
                user.setBalance(rs.getString(12));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int updateuser(User user) {
        int rows = 0;
        String sql = "update [user] set password=?,sex=?,birthday=?,identitycard=?,email=?,mobile=?,address=?,status=?,image=?,balance=? where username=?";
        try {
            con = JDBCUtil.getconn();
        } catch (SQLException ex) {
            Logger.getLogger(UserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            stim = con.prepareStatement(sql);
            stim.setString(1, user.getPassword());
            stim.setString(2, user.getSex());
            stim.setString(3, user.getBirthday());
            stim.setString(4, user.getIdentitycard());
            stim.setString(5, user.getEmail());
            stim.setString(6, user.getMobile());
            stim.setString(7, user.getAddress());
            stim.setInt(8, user.getStatus());
            stim.setString(9, user.getImage());
            stim.setString(10, user.getBalance());
            stim.setString(11, user.getUsername());

            rows = stim.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rows;
    }
}
