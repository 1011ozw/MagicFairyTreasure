/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao;

import com.mft.Bean.Product;
import com.mft.Bean.ProductCategory;
import com.mft.Bean.ProductParent;
import java.util.List;

/**
 *
 * @author 张帆
 */
public interface ProductDao {

    public List<Product> getAllProductsByPage(int currentPage, int pageSize);//查询全部

    public List<Product> getProductsByPage(int currentPage, int pageSize, int ppid, int pcid);//根据分类查询商品

    public List<Product> getProductsByPage(int currentPage, int pageSize, int ppid, int pcid, String terrace, String price, String size, String hardpan, String videocard, String name);//分页查询

    public int getCount(int ppid, int pcid, String terrace, String price, String size, String hardpan, String videocard, String name);//得到符合条件商品总数

    public Product getProductById(int id);//根据id查询商品

    public int countProducts(int pcid, int ppid);//得到总商品数

    public List<Product> getProductsInTime();//得到最新商品

    public List<Product> getProductsInPrice();//得到低价商品

    public List<Product> getProductsInPriceOne();//得到库存商品

    public List<Product> getProductsInPriceHiddle();//得到商品

    public List<Product> getProductsInPrice1();//限时抢购

    public List<Product> getProductsInParseInt();//限时抢购2

    public List<String> queryby(String keyword);

    public String getusernames(String keyword);

    public List<Product> show(int a, int stock, int notinID, int pageSize, int pageIndex);

    public List<ProductParent> showProductParent();

    public List<ProductCategory> showProductCategory(int id);

    int addGood(Product p);

    int delete(int id);

    int update(Product p);
}
