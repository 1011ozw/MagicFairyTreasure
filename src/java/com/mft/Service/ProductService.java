/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Service;

import com.mft.Bean.Product;
import com.mft.Bean.ProductCategory;
import com.mft.Bean.ProductParent;
import com.mft.Dao.Impl.ProductImpl;
import com.mft.Dao.ProductDao;
import java.util.List;

/**
 *
 * @author 张帆
 */
public class ProductService {

    ProductDao pd = new ProductImpl();

    public List<Product> getAllProductsByPage(int currentPage, int pageSize) {
        return pd.getAllProductsByPage(currentPage, pageSize);
    }

    public List<Product> getProductsByPage(int currentPage, int pageSize,
            int ppid, int pcid, String terrace, String price, String size,
            String hardpan, String videocard, String name) {
        return pd.getProductsByPage(currentPage, pageSize, ppid, pcid, terrace, price, size, hardpan, videocard, name);
    }
    //分页

    public int getCount(int ppid, int pcid, String terrace, String price,
            String size, String hardpan, String videocard, String name) {
        return pd.getCount(ppid, pcid, terrace, price, size, hardpan, videocard, name);
    }

    public Product getProductById(int id) {
        return pd.getProductById(id);
    }

    public int countProducts(int pcid, int ppid) {
        return pd.countProducts(pcid, ppid);
    }

    public List<Product> getProductsInPrice() {
        return pd.getProductsInPrice();
    }

    public List<Product> getProductsInTime() {
        return pd.getProductsInTime();
    }

    public List<Product> getProductsByPage(int currentPage, int pageSize,
            int ppid, int pcid) {
        return pd.getProductsByPage(currentPage, pageSize, ppid, pcid);
    }

    //
    public List<Product> getProductsInPriceOne() {
        return pd.getProductsInPriceOne();
    }

    public List<Product> getProductsInPriceHiddle() {
        return pd.getProductsInPriceHiddle();
    }

    public List<Product> getProductsInPrice1() {
        return pd.getProductsInPrice1();
    }

    public List<Product> getProductsInParseInt() {
        return pd.getProductsInParseInt();
    }

    public List<ProductParent> showProductParent() {
        //显示商品一级分类表
        return pd.showProductParent();
    }

    public List<ProductCategory> showProductCategory(int id) {
        //显示商品二级分类表
        return pd.showProductCategory(id);
    }

    public int addGood(Product p) {
        //添加商品
        return pd.addGood(p);
    }

    public List<Product> show(int id, int stock, int notInId, int pageSize, int pageIndex) {
        //查询商品信息表
        return pd.show(id, stock, notInId, pageSize, pageIndex);
    }

    public int delete(int id) {
        //删除商品
        return pd.delete(id);
    }

    public int update(Product p) {
        return pd.update(p);
    }

    public String getusernames(String keyword) {
        return pd.getusernames(keyword);
    }
}
