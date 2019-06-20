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
public class Comment {

    private static final long serialVersionUID = 1L;
    private int id;
    private String reply;
    private String content;
    private String createtime;
    private String replytime;
    private String nickname;
    private int status;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getReply() {
        return reply;
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

    public void setReplytime(String replytime) {
        this.replytime = replytime;
    }

    public String getReplytime() {
        return replytime;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
    //构造方法

    public Comment() {
    }

    public Comment(int id, String reply, String content, String createtime, String replytime, String nickname, int status) {
        this.id = id;
        this.reply = reply;
        this.content = content;
        this.createtime = createtime;
        this.replytime = replytime;
        this.nickname = nickname;
        this.status = status;
    }
}
