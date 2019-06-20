/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao.Impl;

import com.mft.Bean.Cart;
import com.mft.Dao.CartDao;
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
public class CartImpl implements CartDao {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     *
     * @param c
     * @return
     */
    @Override
    public int addCart(Cart c) {
        // TODO Auto-generated method stub
        int r = 0;
        try {
            conn = JDBCUtil.getconn();
            String sql = "insert into cart(uid,pid,quantity) values(" + c.getUid() + "," + c.getPid() + "," + c.getQuantity() + ")";
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

    @Override
    public Cart isExistsProduct(int uid, int pid) {
        // TODO Auto-generated method stub
        Cart c = null;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select * from cart where uid=" + uid + " and pid=" + pid;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                c = new Cart();
                c.setId(rs.getInt(1));
                c.setUid(rs.getInt(2));
                c.setPid(rs.getInt(3));
                c.setQuantity(rs.getInt(4));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return c;
    }

    @Override
    public int upCart(Cart c) {
        // TODO Auto-generated method stub
        int r = 0;
        try {
            conn = JDBCUtil.getconn();
            String sql = "update cart set quantity=" + c.getQuantity() + " where uid=" + c.getUid() + " and pid=" + c.getPid();
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

    @Override
    public List<Cart> getCartsByUid(int uid) {
        // TODO Auto-generated method stub
        List<Cart> carts = null;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select cart.id,uid,pid,quantity,product.image,product.price,product.name from cart,product where cart.pid=product.id and uid=" + uid;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            carts = new ArrayList<Cart>();
            while (rs.next()) {
                Cart c = new Cart(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7));
                carts.add(c);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return carts;
    }

    @Override
    public int delCartByPid(int pid, int uid) {
        // TODO Auto-generated method stub
        int r = 0;
        try {
            conn = JDBCUtil.getconn();
            String sql = "delete from cart where pid=" + pid + " and uid=" + uid;
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
