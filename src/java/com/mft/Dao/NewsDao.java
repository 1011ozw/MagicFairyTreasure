/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao;

import com.mft.Bean.News;
import java.util.List;

/**
 *
 * @author 张帆
 */
public interface NewsDao {
	public List<News>getNews();//得到商品
	public News getNewsById(int id);//根据id查询商品
	
	public List<News>getNewsByPage(int currentPage,int pageSize);//分页显示新闻
	public int addNews(News n);//添加新闻
	public int upNews(News n);//修改新闻
	public int delNews(int id);//根据id删除新闻
	public int getCountNews();//得到新闻总数
}
