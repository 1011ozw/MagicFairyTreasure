/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Service;

import com.mft.Bean.Cart;
import com.mft.Dao.CartDao;
import com.mft.Dao.Impl.CartImpl;
import java.util.List;

/**
 *
 * @author 张帆
 */
public class CartService {

    //实例化购物车的实现类对象
    CartDao cd = new CartImpl();
    //向购物车中添加商品

    public int addCart(Cart c) {
        return cd.addCart(c);
    }
    //判断商品是否存在于购物车

    public Cart isExistsProduct(int uid, int pid) {
        return cd.isExistsProduct(uid, pid);
    }
    //修改购物车中的商品

    public int upCart(Cart c) {
        return cd.upCart(c);
    }
    //根据用户id查询购物车

    public List<Cart> getCartsByUid(int uid) {
        return cd.getCartsByUid(uid);
    }
    //根据商品编号删除购物信息

    public int delCartByPid(int pid, int uid) {
        return cd.delCartByPid(pid, uid);
    }
}
