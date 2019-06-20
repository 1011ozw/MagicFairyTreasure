/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Service;

import com.mft.Bean.News;
import com.mft.Dao.Impl.NewsImpl;
import com.mft.Dao.NewsDao;
import java.util.List;

/**
 *
 * @author 张帆
 */
public class NewsService {

    //实例化新闻的实现类对象
    NewsDao nd = new NewsImpl();
    //得到商品

    public List<News> getNews() {
        return nd.getNews();
    }
    //根据id查询商品

    public News getNewsById(int id) {
        return nd.getNewsById(id);
    }
    //添加新闻

    public int addNews(News n) {
        return nd.addNews(n);
    }
    //分页显示新闻

    public List<News> getNewsByPage(int currentPage, int pageSize) {
        return nd.getNewsByPage(currentPage, pageSize);
    }
    //修改新闻

    public int upNews(News n) {
        return nd.upNews(n);
    }
    //删除新闻

    public int delNews(int id) {
        return nd.delNews(id);
    }
    //获取新闻的条数

    public int getCountNews() {
        return nd.getCountNews();
    }
}
