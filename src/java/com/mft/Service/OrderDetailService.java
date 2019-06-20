/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Service;

import com.mft.Bean.OrderDetail;
import com.mft.Dao.Impl.OrderDetailImpl;
import com.mft.Dao.OrderDetailDao;
import java.util.List;

/**
 *
 * @author 张帆
 */
public class OrderDetailService {

    //实现订单对象的实现类
    OrderDetailDao od = new OrderDetailImpl();
    //添加订单明细

    public int addOrderDetail(OrderDetail o) {
        return od.addOrderDetail(o);
    }
    //根据订单号进行查询

    public List<OrderDetail> getOrderDetailsByOid(int oid) {
        return od.getOrderDetailsByOid(oid);
    }
    //根据订单号删除订单明细

    public int delOrderDetailByOid(int oid) {
        return od.delOrderDetailByOid(oid);
    }
}
