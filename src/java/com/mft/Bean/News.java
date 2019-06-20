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
public class News {

    private static final long serialVersionUID = 1L;
    private int id;
    private String title;
    private String content;
    private String createtime;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCreatetime() {
        return createtime;
    }
    //构造方法

    public News() {
    }

    public News(int id, String title, String content, String createtime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createtime = createtime;
    }
}
