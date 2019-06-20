/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao;

import com.mft.Bean.User;
import java.util.List;

/**
 *
 * @author 张帆
 */
public interface UserDao {
	int addUser(User u);//添加用户
	User checkLogin(String name,String pwd);//验证用户登录
	boolean isUserByName(String name);//判断用户是否存在
	
	User login(String userName,String userPwd,int type);
	List<User> userId(int pageIndex,int pageSum);
	User user(int userId);
	User bynameuser(String username);
	int updateuser(User user); 
}

