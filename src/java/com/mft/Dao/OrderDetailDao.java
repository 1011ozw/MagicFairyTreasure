/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao;

import com.mft.Bean.OrderDetail;
import java.util.List;

/**
 *
 * @author 张帆
 */
public interface OrderDetailDao {
	public int addOrderDetail(OrderDetail o);//添加订单明细
	public List<OrderDetail>getOrderDetailsByOid(int oid);//根据订单号查询
	public int delOrderDetailByOid(int oid);//根据订单号删除订单明细
}
