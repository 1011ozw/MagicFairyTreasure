/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao.Impl;

import com.mft.Bean.Comment;
import com.mft.Dao.CommentDao;
import com.mft.Util.JDBCUtil;

/**
 *
 * @author 张帆
 */
public class CommentImpl implements CommentDao {

    @Override
    public int addCmt(Comment c) {
        // TODO Auto-generated method stub
        String sql = "insert into comment values(?,?,?,?,?,?)";
        Object[] os = {c.getReply(), c.getContent(), c.getCreatetime(), c.getReplytime(), c.getNickname(), c.getStatus()};
        int r = JDBCUtil.executeUpdate(sql, os);
        return r;
    }

    @Override
    public int delCmt(int id) {
        // TODO Auto-generated method stub
        String sql = "delete from comment where id=" + id;
        int r = JDBCUtil.executeUpdate(sql, null);
        return r;
    }

}
