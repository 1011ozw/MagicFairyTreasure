/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao.Impl;

import com.mft.Bean.News;
import com.mft.Dao.NewsDao;
import com.mft.Util.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 张帆
 */
public class NewsImpl implements NewsDao{

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public List<News> getNews() {
        // TODO Auto-generated method stub
        List<News> news = null;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select top 20 * from news order by createtime desc";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            news = new ArrayList<News>();
            while (rs.next()) {
                News n = new News(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                news.add(n);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return news;
    }

    public News getNewsById(int id) {
        // TODO Auto-generated method stub
        News n = null;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select * from news where id=" + id;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                n = new News(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return n;
    }

    public int addNews(News n) {
        // TODO Auto-generated method stub
        String sql = "insert into news(title,content) values('" + n.getTitle() + "','" + n.getContent() + "')";
        int r = JDBCUtil.executeUpdate(sql, null);
        return r;
    }

    public List<News> getNewsByPage(int currentPage, int pageSize) {
        // TODO Auto-generated method stub
        List<News> news = null;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select top " + pageSize + " * from news where id not in (select top " + (currentPage - 1) * pageSize + " id from news order by createtime desc)order by createtime desc";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            news = new ArrayList<News>();
            while (rs.next()) {
                News n = new News(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                news.add(n);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return news;
    }

    public int upNews(News n) {
        // TODO Auto-generated method stub
        String sql = "update news set title='" + n.getTitle() + "',content='" + n.getContent() + "' where id=" + n.getId();
        int r = JDBCUtil.executeUpdate(sql, null);
        return r;
    }

    public int delNews(int id) {
        // TODO Auto-generated method stub
        String sql = "delete from news where id=" + id;
        int r = JDBCUtil.executeUpdate(sql, null);
        return r;
    }

    public int getCountNews() {
        // TODO Auto-generated method stub
        int count = 0;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select count(*) from news";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return count;
    }
}
