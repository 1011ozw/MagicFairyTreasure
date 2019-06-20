/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Bean;

import java.util.List;

/**
 *
 * @author 张帆
 */
public class OrderHelper {

    private static final long serialVersionUID = 1L;
    private int oid;//订单编号
    private List<OrderDetail> os;//订单商品
    private String uname;//收货人姓名
    private String money;//订单金额
    private String time;//下单时间
    private int state;//订单状态

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public List<OrderDetail> getOs() {
        return os;
    }

    public void setPs(List<OrderDetail> os) {
        this.os = os;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public OrderHelper(int oid, List<OrderDetail> os, String uname, String money,
            String time, int state) {
        super();
        this.oid = oid;
        this.os = os;
        this.uname = uname;
        this.money = money;
        this.time = time;
        this.state = state;
    }
}
