/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Service;

import com.mft.Bean.Comment;
import com.mft.Dao.CommentDao;
import com.mft.Dao.Impl.CommentImpl;

/**
 *
 * @author 张帆
 */
public class CommentService {
    //实现留言对象的实例化的对象
	CommentDao cd=new CommentImpl();
	//添加留言
	public int addCmt(Comment c) {
		return cd.addCmt(c);
	}
	//删除留言
	public int delCmt(int id) {
		return cd.delCmt(id);
	}
}
