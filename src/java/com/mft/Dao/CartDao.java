/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao;

import com.mft.Bean.Cart;
import java.util.List;

/**
 *
 * @author 张帆
 */
public interface CartDao {
	int addCart(Cart c);//把商品放入购物车
	Cart isExistsProduct(int uid,int pid);//判断商品是否存在
	int upCart(Cart c);//更新购物车中商品
	List<Cart>getCartsByUid(int uid);//根据用户id查询购物车
	int delCartByPid(int pid,int uid);//根据商品编号删除购物信息
}
