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
public class Cart {

    private static final long serialVersionUID = 1L;
    private int id;
    private int uid;
    private int pid;
    private int quantity;
    private String image;
    private String price;
    private String name;

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

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getPid() {
        return pid;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//构造方法

    public Cart() {
    }

    public Cart(int id, int uid, int pid, int quantity, String image, String price, String name) {
        this.id = id;
        this.uid = uid;
        this.pid = pid;
        this.quantity = quantity;
        this.image = image;
        this.price = price;
        this.name = name;
    }
}
