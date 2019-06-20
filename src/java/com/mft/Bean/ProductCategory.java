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
public class ProductCategory {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private int parentid;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public int getParentid() {
        return parentid;
    }
    //构造方法

    public ProductCategory() {
    }

    public ProductCategory(int id, String name, int parentid) {
        this.id = id;
        this.name = name;
        this.parentid = parentid;
    }
}
