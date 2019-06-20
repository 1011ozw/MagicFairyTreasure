/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao.Impl;

import com.mft.Bean.Order;
import com.mft.Bean.OrderDetail;
import com.mft.Bean.OrderHelper;
import com.mft.Dao.OrderDao;
import com.mft.Service.OrderDetailService;
import com.mft.Util.JDBCUtil;
import com.mft.Util.UtilHelper;
import com.mft.Util.idusHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 张帆
 */
public class OrderImpl implements OrderDao {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Override
    public int addOrder(Order o) {
        // TODO Auto-generated method stub
        String sql = "insert into [order] values(?,?,?,?,?,?,?,?,?)";
        Object[] os = {o.getUid(), o.getUsername(), o.getAddress(), o.getCreatetime(), o.getCost(), o.getStatus(), o.getType(), o.getPhone(), o.getEmail()};
        int r = JDBCUtil.executeUpdate(sql, os);
        return r;
    }

    @Override
    public int getOid() {
        // TODO Auto-generated method stub
        int r = 0;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select max(id)from [order]";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                r = rs.getInt(1);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return r;
    }

    @Override
    public List<OrderHelper> getOrderHelpers(int uid) {
        // TODO Auto-generated method stub
        List<OrderHelper> os = null;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select id,username,cost,createtime,status from [order] where uid=" + uid + " order by createtime desc";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            os = new ArrayList<OrderHelper>();
            while (rs.next()) {
                int oid = rs.getInt(1);
                List<OrderDetail> ls = new OrderDetailService().getOrderDetailsByOid(oid);
                OrderHelper o = new OrderHelper(rs.getInt(1), ls, rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
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
    public Order getOrderByOid(int oid) {
        // TODO Auto-generated method stub
        Order o = null;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select * from [order] where id=" + oid;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                o = new Order(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return o;
    }

    @Override
    public int delOrderById(int id) {
        // TODO Auto-generated method stub
        int r = 0;
        try {
            conn = JDBCUtil.getconn();
            String sql = "delete from [order] where id=" + id;
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
    public int upOrderById(int id, int status) {
        // TODO Auto-generated method stub
        int r = 0;
        try {
            conn = JDBCUtil.getconn();
            String sql = "update [order] set status=" + status + " where id=" + id;
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

    Connection con = null;
    PreparedStatement stim = null;

    @Override
    public List<Order> QueryAllOrder(int type, int pageIndex, int pageSize) {
        // TODO 查询所有订单
        List<Order> Orders = null;
        try {
            con = JDBCUtil.getconn();
            String sql = "";
            if (type == 0) {//查看所有订单
                sql = "select top " + pageSize + " * from [order] where id not in (select top " + pageIndex * pageSize + " id from [order] order by createtime desc) order by createtime desc";
            } else if (type == 1) {//查看待发货订单
                sql = "select top " + pageSize + " * from [order] where status=0 and id not in (select top " + pageIndex * pageSize + " id from [order] where status=0 order by createtime desc) order by createtime desc";
            } else if (type == 2) {//查看已发货订单
                sql = "select top " + pageSize + " * from [order] where status=1 and id not in (select top " + pageIndex * pageSize + " id from [order] where status=1 order by createtime desc) order by createtime desc";
            } else if (type == 3) {//查看交易成功订单
                sql = "select top " + pageSize + " * from [order] where status=2 and id not in (select top " + pageIndex * pageSize + " id from [order] where status=2 order by createtime desc) order by createtime desc";
            } else {//查看取消订单
                sql = "select top " + pageSize + " * from [order] where status=3 and id not in (select top " + pageIndex * pageSize + " id from [order] where status=3 order by createtime desc) order by createtime desc";
            }
            stim = con.prepareStatement(sql);
            rs = stim.executeQuery();
            Orders = new ArrayList<Order>();
            while (rs.next()) {
                Order o = new Order(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10));
                Orders.add(o);
            }
        } catch (Exception e) {
            // TODO: 异常处理
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return Orders;
    }

    @Override
    public int admin_addOrder(Order o) {
        // TODO 增加用户
        int r = -1;
        try {
            con = JDBCUtil.getconn();
            String sql = "insert into [order]([uid],username,[address],cost,[type],status) values(?,?,?,?,?,1)";
            stim = con.prepareStatement(sql);
            stim.setInt(1, o.getUid());
            stim.setString(2, o.getUsername());
            stim.setString(3, o.getAddress());
            stim.setString(4, o.getCost());
            stim.setInt(5, o.getType());
            r = stim.executeUpdate();
        } catch (Exception e) {
            // TODO: 异常处理
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return r;
    }

    public int upodateStock(int oid) {
        // TODO 商品发货减去库存数量
        int r = -1;
        try {
            con = JDBCUtil.getconn();
            OrderDetail od = UtilHelper.showSumOrder(oid);
            String sql = "update product set stock=stock-" + od.getQuantity() + " where id=" + od.getPid();
            stim = con.prepareStatement(sql);
            r = stim.executeUpdate();
        } catch (Exception e) {
            // TODO: 异常处理
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return r;
    }

    @Override
    public int updateOrder(int id, String type) {
        // TODO 增删改订单表
        int r = -1;
        try {
            String sql = "";
            con = JDBCUtil.getconn();
            if (type.equals("d")) {//删除
                sql = "delete [order] where id=" + id;
            } else if (type.equals("u")) {//订单发货
                OrderImpl oi = new OrderImpl();
                int a = oi.upodateStock(id);
                if (a > 0) {
                    System.out.println("商品库存成功减少.");
                    sql = "update [order]  set [status]=1 where id=" + id;
                }
            } else if (type.equals("u1")) {//取消订单
                sql = "update [order]  set [status]=3 where id=" + id;
            }
            stim = con.prepareStatement(sql);
            r = idusHelper.idu(sql);//调用增删改通用方法
        } catch (Exception e) {
            // TODO: 异常处理
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return r;
    }

    @Override
    public int getCountOrdersByStatus(int status) {
        // TODO Auto-generated method stub
        int r = 0;
        try {
            con = JDBCUtil.getconn();
            String sql = "select count(*) from [order] where status=" + status;
            stim = con.prepareStatement(sql);
            rs = stim.executeQuery();
            if (rs.next()) {
                r = rs.getInt(1);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return r;
    }
}
