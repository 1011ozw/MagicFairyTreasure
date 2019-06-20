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
public class ProductParent {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;

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
    //构造方法

    public ProductParent() {
    }

    public ProductParent(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
