/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Bean;

/**
 *
 * @author 张帆
 */
public class Order {

    private static final long serialVersionUID = 1L;
    private int id;
    private int uid;
    private String username;
    private String address;
    private String createtime;
    private String cost;
    private int status;
    private int type;
    private String phone;
    private String email;
    private int oid;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCost() {
        return cost;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
//构造方法

    public Order() {
    }

    public Order(int id, int uid, String username, String address, String createtime, String cost, int status, int type, String phone, String email) {
        this.id = id;
        this.uid = uid;
        this.username = username;
        this.address = address;
        this.createtime = createtime;
        this.cost = cost;
        this.status = status;
        this.type = type;
        this.phone = phone;
        this.email = email;
    }

    public Order(int id, int uid, String username, String address, String createtime, String cost, int status, int type) {
        this.id = id;
        this.uid = uid;
        this.username = username;
        this.address = address;
        this.createtime = createtime;
        this.cost = cost;
        this.status = status;
        this.type = type;
    }
}
