/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao.Impl;

import com.mft.Bean.Product;
import com.mft.Bean.ProductCategory;
import com.mft.Bean.ProductParent;
import com.mft.Dao.ProductDao;
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
public class ProductImpl implements ProductDao {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public List<Product> getAllProductsByPage(int currentPage, int pageSize) {
        // TODO Auto-generated method stub
        List<Product> products = null;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select top " + pageSize
                    + " * from product where id not in(select top "
                    + (currentPage - 1) * pageSize + " id from product) ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            products = new ArrayList<Product>();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5),
                        rs.getInt(6), rs.getInt(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11),
                        rs.getString(12), rs.getString(13));
                products.add(p);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return products;
    }

    @Override
    public List<Product> getProductsByPage(int currentPage, int pageSize,
            int ppid, int pcid, String terrace, String price, String size,
            String hardpan, String videocard, String name) {
        // TODO Auto-generated method stub
        List<Product> products = null;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select top " + pageSize
                    + " * from product where  ppid=" + ppid + " and pcid="
                    + pcid + " and terrace like '%" + terrace
                    + "%' and hardpan like '%" + hardpan
                    + "%' and videocard like '%" + videocard
                    + "%'and name like '%" + name + "%' and size like '%"
                    + size + "%'" + "" + price + "and id not in(select top "
                    + (currentPage - 1) * pageSize
                    + " id from product where  ppid=" + ppid + " and pcid="
                    + pcid + " and terrace like '%" + terrace
                    + "%' and hardpan like '%" + hardpan
                    + "%' and videocard like '%" + videocard
                    + "%'and name like '%" + name + "%' and size like '%"
                    + size + "%'" + "" + price + ") ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            products = new ArrayList<Product>();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5),
                        rs.getInt(6), rs.getInt(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11),
                        rs.getString(12), rs.getString(13));
                products.add(p);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return products;
    }

    @Override
    public List<Product> getProductsByPage(int currentPage, int pageSize,
            int ppid, int pcid) {
        // TODO Auto-generated method stub
        List<Product> products = null;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select top " + pageSize
                    + " * from product where  ppid=" + ppid + " and pcid="
                    + pcid + " and id not in(select top " + (currentPage - 1)
                    * pageSize + " id from product where  ppid=" + ppid
                    + " and pcid=" + pcid + ") ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            products = new ArrayList<Product>();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5),
                        rs.getInt(6), rs.getInt(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11),
                        rs.getString(12), rs.getString(13));
                products.add(p);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return products;
    }

    public int getCount(int ppid, int pcid, String terrace, String price,
            String size, String hardpan, String videocard, String name) {
        // TODO Auto-generated method stub
        int count = 0;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select count(*) from product where  ppid=" + ppid
                    + " and pcid=" + pcid + " and terrace like '%" + terrace
                    + "%' and hardpan like '%" + hardpan
                    + "%' and videocard like '%" + videocard
                    + "%'and name like '%" + name + "%' and size like '%"
                    + size + "%'" + "" + price;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
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

    @Override
    public Product getProductById(int id) {
        // TODO Auto-generated method stub
        Product p = null;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select * from product where id=" + id;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getInt(6),
                        rs.getInt(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getString(11), rs.getString(12),
                        rs.getString(13));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return p;
    }

    @Override
    public int countProducts(int pcid, int ppid) {
        // TODO Auto-generated method stub
        int count = 0;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select count(*) from product where pcid=" + pcid
                    + "and ppid=" + ppid;
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

    public List<Product> getProductsInPrice() {
        // TODO Auto-generated method stub
        // 定义一个products集合
        List<Product> products = null;
        try {

            conn = JDBCUtil.getconn();
            String sql = "select top 5 * from product order by price asc";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            products = new ArrayList<Product>();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5),
                        rs.getInt(6), rs.getInt(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11),
                        rs.getString(12), rs.getString(13));
                products.add(p);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return products;
    }

    /**
     * 限时抢购NO.1
     */
    public List<Product> getProductsInPrice1() {
        // TODO Auto-generated method stub
        // 定义一个products集合
        List<Product> products = null;
        try {

            conn = JDBCUtil.getconn();
            String sql = "select top 1 * from product where id not in(select top 5 id from product order by price) order by price";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            products = new ArrayList<Product>();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5),
                        rs.getInt(6), rs.getInt(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11),
                        rs.getString(12), rs.getString(13));
                products.add(p);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return products;
    }

    /**
     * 限时抢购NO.2
     */
    public List<Product> getProductsInParseInt() {
        // TODO Auto-generated method stub
        // 定义一个products集合
        List<Product> products = null;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select top 1 * from product where id not in(select top 5 id from product order by createtime) order by createtime";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            products = new ArrayList<Product>();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5),
                        rs.getInt(6), rs.getInt(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11),
                        rs.getString(12), rs.getString(13));
                products.add(p);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return products;
    }

    /**
     * 按库存连接图书
     */
    public List<Product> getProductsInPriceOne() {
        // TODO Auto-generated method stub
        // 定义一个products集合
        List<Product> products = null;
        try {

            conn = JDBCUtil.getconn();
            String sql = "select top 5 * from product where id not in(select top 40 id from product order by stock) order by stock asc";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            products = new ArrayList<Product>();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5),
                        rs.getInt(6), rs.getInt(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11),
                        rs.getString(12), rs.getString(13));
                products.add(p);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return products;
    }

    /**
     *
     */
    public List<Product> getProductsInPriceHiddle() {
        // TODO Auto-generated method stub
        // 定义一个products集合
        List<Product> products = null;
        try {

            conn = JDBCUtil.getconn();
            String sql = "select top 5 * from product where id not in(select top 10 id from product order by price) order by price desc";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            products = new ArrayList<Product>();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5),
                        rs.getInt(6), rs.getInt(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11),
                        rs.getString(12), rs.getString(13));
                products.add(p);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return products;
    }

    public List<Product> getProductsInTime() {
        // TODO Auto-generated method stub
        List<Product> products = null;
        try {
            conn = JDBCUtil.getconn();
            String sql = "select top 5 * from product order by createtime desc";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            products = new ArrayList<Product>();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5),
                        rs.getInt(6), rs.getInt(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11),
                        rs.getString(12), rs.getString(13));
                products.add(p);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, pstmt, rs);
        }
        return products;
    }

    Connection con = null;
    PreparedStatement stim = null;

    public List<Product> show(int id, int stock, int notInId, int pageSize,
            int pageIndex) {
        // TODO 查询所有ID或者所有商品
        List<Product> products = null;
        try {
            con = JDBCUtil.getconn();
            String sql = "select top "
                    + pageSize
                    + " * from product where id not in(select top "
                    + (pageSize * pageIndex)
                    + " id from product  order by createtime desc)  order by createtime desc";

            if (id != 0) {
                // 根据ID查询
                System.out.println("根据ID查询");
                sql = "select * from product where id=" + id
                        + " order by createtime desc";
            }
            if (stock != 0) {// 查询没有库存的商品
                System.out.println("查询没有库存的商品");
                sql = "select top "
                        + pageSize
                        + " * from product where stock=0  and id not in"
                        + "(select top "
                        + (pageIndex * pageSize)
                        + " id from product where stock=0   order by createtime desc) order by createtime desc";
            }
            if (notInId != 0) {
                // 查询没有订单的商品
                System.out.println("查询没有订单的商品");
                sql = "select top "
                        + pageSize
                        + " * from product where id not in"
                        + "(select pid from [orderDetail] ) and id not in"
                        + "(select top "
                        + (pageIndex * pageSize)
                        + " id from product where id not in"
                        + "(select pid from [orderDetail] ) order by createtime desc ) order by createtime desc";
            }
            System.out.println(sql);
            stim = con.prepareStatement(sql);
            rs = stim.executeQuery();
            products = new ArrayList<Product>();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPrice(rs.getString(4));
                p.setStock(rs.getInt(5));
                p.setImage(rs.getString(8));
                products.add(p);
            }
        } catch (Exception e) {
            // TODO: 异常处理
            e.printStackTrace();
        } finally {
            JDBCUtil.release(con, stim, rs);
        }
        return products;
    }

    @Override
    public List<ProductParent> showProductParent() {
        // TODO 显示一级分类表
        List<ProductParent> ProductParents = null;
        try {
            con = JDBCUtil.getconn();
            String sql = "select * from productParent";
            stim = con.prepareStatement(sql);
            rs = stim.executeQuery();
            ProductParents = new ArrayList<ProductParent>();
            while (rs.next()) {
                ProductParent pp = new ProductParent();
                pp.setId(rs.getInt(1));
                pp.setName(rs.getString(2));
                ProductParents.add(pp);
            }
        } catch (Exception e) {
            // TODO: 异常处理
            e.printStackTrace();
        } finally {
            JDBCUtil.release(con, stim, rs);
        }
        return ProductParents;
    }

    @Override
    public List<ProductCategory> showProductCategory(int id) {
        // TODO 异常处理
        List<ProductCategory> ProductCategorys = null;
        try {
            con = JDBCUtil.getconn();
            String sql = "select * from productCategory where parentid =" + id;
            stim = con.prepareStatement(sql);
            rs = stim.executeQuery();
            ProductCategorys = new ArrayList<ProductCategory>();
            while (rs.next()) {
                ProductCategory pc = new ProductCategory();
                pc.setId(rs.getInt(1));
                pc.setName(rs.getString(2));
                pc.setParentid(rs.getInt(3));
                ProductCategorys.add(pc);
            }
        } catch (Exception e) {
            // TODO:异常处理
            e.printStackTrace();
        } finally {
            JDBCUtil.release(con, stim, rs);
        }
        return ProductCategorys;
    }

    public int addGood(Product p) {
        // TODO 添加商品
        int r = -1;// 返回值
        try {
            con = JDBCUtil.getconn();
            String size = null;
            String terrace = null;
            String hardpan = null;
            String videocard = null;
            if (p.getSize() != null) {
                size = p.getSize();
                terrace = p.getTerrace();
                hardpan = p.getHardpan();
                videocard = p.getVideocard();
            }
            String sql = "insert into product(name,[description]"
                    + ",price,[stock],ppid,pcid,[image]"
                    + ",size,terrace,hardpan,videocard) " + "values("
                    + p.getName()
                    + ","
                    + p.getDescription()
                    + ","
                    + p.getPrice()
                    + ","
                    + p.getStock()
                    + ""
                    + ","
                    + p.getPpid()
                    + ","
                    + p.getPcid()
                    + ","
                    + p.getImage()
                    + ""
                    + ","
                    + size
                    + ","
                    + terrace
                    + ","
                    + hardpan
                    + ","
                    + videocard + ")";
            System.out.println(sql);
            stim = con.prepareStatement(sql);
            r = stim.executeUpdate();
        } catch (Exception e) {
            // TODO: 异常处理
            e.printStackTrace();
        } finally {
            JDBCUtil.release(con, stim, rs);
        }
        return r;
    }

    @Override
    public int delete(int id) {
        // TODO 删除商品
        int r = -1;
        try {
            con = JDBCUtil.getconn();
            String sql = "delete  from product where id=" + id;
            stim = con.prepareStatement(sql);
            r = stim.executeUpdate();
        } catch (Exception e) {
            // TODO: 异常处理
            e.printStackTrace();
        } finally {
            JDBCUtil.release(con, stim, rs);
        }
        return r;
    }

    @Override
    public int update(Product p) {
        // TODO 修改商品
        int r = -1;// 返回值
        try {
            con = JDBCUtil.getconn();
            String sql = "update product set name='" + p.getName() + "',"
                    + "[description]='" + p.getDescription() + "',price='"
                    + p.getPrice() + "',stock='" + p.getStock() + "',"
                    + "size='" + p.getSize() + "',terrace='" + p.getTerrace()
                    + "',hardpan='" + p.getHardpan() + "',videocard='" + ""
                    + p.getVideocard() + "' where id=" + p.getId() + "";
            stim = con.prepareStatement(sql);
            r = stim.executeUpdate();
        } catch (Exception e) {
            // TODO: 异常处理
            e.printStackTrace();
        } finally {
            JDBCUtil.release(con, stim, rs);
        }
        return r;
    }

    @Override
    public List<String> queryby(String keyword) {
        // TODO Auto-generated method stub

        List<String> names = new ArrayList<String>();
        try {
            con = JDBCUtil.getconn();
            String sql = "select name from product where name like'" + keyword
                    + "%'";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                // 从数据库取出一条记录然后在把数据放入实体对象中
                String name = rs.getString(1);
                names.add(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return names;
    }

    @Override
    public String getusernames(String keyword) {
        // TODO Auto-generated method stub
        ProductDao d = new ProductImpl();
        List<String> names = d.queryby(keyword);
        String res = "";
        if (names.size() > 0) {
            for (String name : names) {
                res += name + ",";
            }
            return res.substring(0, res.length() - 1);
        }
        return null;
    }

}
