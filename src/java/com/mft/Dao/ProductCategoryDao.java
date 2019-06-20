/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao;

import com.mft.Bean.ProductCategory;
import java.util.List;

/**
 *
 * @author 张帆
 */
public interface ProductCategoryDao {
	List<ProductCategory>getPcsByPid(int pid);//得到分类菜单
	String getPcName(int pcid,int pid);//根据id得到分类名称
	
	boolean isPc(int ppid,String name);//判断是否存在
	int addPc(int ppid,String name);//添加
	int upPc(int ppid,int pcid,String name);//修改
	int delPc(int ppid,int pcid);//删除
	int delPcs(int ppid);//根据一级编号删除
}
