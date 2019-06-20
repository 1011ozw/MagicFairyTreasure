/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao;

import com.mft.Bean.Order;
import com.mft.Bean.OrderHelper;
import java.util.List;

/**
 *
 * @author 张帆
 */
public interface OrderDao {
	public int addOrder(Order o);//添加订单
	public int getOid();//得到订单号
	public List<OrderHelper>getOrderHelpers(int uid);//根据用户id得到订单信息
	public Order getOrderByOid(int oid);//根据订单号查询订单
	public int upOrderById(int id,int status);//修改订单状态
	public int delOrderById(int id);//根据订单号删除订单	
	
        List<Order> QueryAllOrder(int type,int pageIndex,int pageSum);
	int updateOrder(int id,String type);
	int admin_addOrder(Order o);
	int getCountOrdersByStatus(int status);//根据订单状态查询订单总数
}
