/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao;

import com.mft.Bean.ProductParent;
import java.util.List;

/**
 *
 * @author 张帆
 */
public interface ProductParetnDao {
	List<ProductParent>getAllPs();//得到所有图书1级分类
	
	int addPp(String name);//添加一级分类
	int upPp(int id,String name);//修改一级分类
	int delPp(int id);//删除一级分类
	boolean isPp(String name);//判断是否存在
}

