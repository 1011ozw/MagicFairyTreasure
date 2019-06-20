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
public class Product {

    private int id;
    private String name;
    private String description;
    private String price;
    private int stock;
    private int ppid;
    private int pcid;
    private String image;
    private String size;
    private String terrace;
    private String hardpan;
    private String videocard;
    private String createtime;

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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setPpid(int ppid) {
        this.ppid = ppid;
    }

    public int getPpid() {
        return ppid;
    }

    public void setPcid(int pcid) {
        this.pcid = pcid;
    }

    public int getPcid() {
        return pcid;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTerrace() {
        return terrace;
    }

    public void setTerrace(String terrace) {
        this.terrace = terrace;
    }

    public String getHardpan() {
        return hardpan;
    }

    public void setHardpan(String hardpan) {
        this.hardpan = hardpan;
    }

    public String getVideocard() {
        return videocard;
    }

    public void setVideocard(String videocard) {
        this.videocard = videocard;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
//构造方法

    public Product() {
    }

    public Product(int id, String name, String description, String price,
            int stock, int ppid, int pcid, String image, String size,
            String terrace, String hardpan, String videocard, String createtime) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.ppid = ppid;
        this.pcid = pcid;
        this.image = image;
        this.size = size;
        this.terrace = terrace;
        this.hardpan = hardpan;
        this.videocard = videocard;
        this.createtime = createtime;
    }

    public Product(int id, String name, String description, String price, int stock, int ppid, int pcid, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.ppid = ppid;
        this.pcid = pcid;
        this.image = image;
    }
}
