/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Service;

import com.mft.Bean.Order;
import com.mft.Bean.OrderHelper;
import com.mft.Dao.Impl.OrderImpl;
import com.mft.Dao.OrderDao;
import java.util.List;

/**
 *
 * @author 张帆
 */
public class OrderService {

    //实现订单对象的实现类对象
    OrderDao od = new OrderImpl();
    //添加订单

    public int addOrder(Order o) {
        return od.addOrder(o);
    }
    //得到订单号

    public int getOid() {
        return od.getOid();
    }
    //根据用户id得到订单信息

    public List<OrderHelper> getOrders(int uid) {
        return od.getOrderHelpers(uid);
    }
    //根据订单号查询订单

    public Order getOrderByOid(int oid) {
        return od.getOrderByOid(oid);
    }
    //根据订单号删除订单

    public int delOrderById(int id) {
        return od.delOrderById(id);
    }
    //修改订单状态

    public int upOrderById(int id, int status) {
        return od.upOrderById(id, status);
    }

    public List<Order> QueryAllOrder(int type, int pageIndex, int pageSize) {
        return od.QueryAllOrder(type, pageIndex, pageSize);
    }

    public int updateOrder(int id, String type) {
        return od.updateOrder(id, type);
    }

    public int admin_addOrder(Order o) {
        return od.addOrder(o);
    }

    public int getCountOrdersByStatus(int status) {
        return od.getCountOrdersByStatus(status);
    }
}
