/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao;

import com.mft.Bean.Comment;

/**
 *
 * @author 张帆
 */
public interface CommentDao {
	public int addCmt(Comment c);//添加留言
	public int delCmt(int id);//删除留言
}

