/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Servlet;

import com.mft.Bean.Cart;
import com.mft.Bean.Product;
import com.mft.Bean.User;
import com.mft.Service.CartService;
import com.mft.Service.ProductService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 张帆
 */
public class PutCartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs 放入购物车
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int pid = Integer.parseInt(request.getParameter("pid"));//得到商品编号
        ProductService ps = new ProductService();
        Product p = ps.getProductById(pid);//根据编号得到商品对象
        int sl = Integer.parseInt(request.getParameter("buyamount"));//得到数量
        HttpSession session = request.getSession();
        List<Cart> cs = (List<Cart>) session.getAttribute("cart");//获取购物车中商品信息
        User u = (User) session.getAttribute("user");
        if (u == null) { //用户未登录
            if (cs == null) {
                cs = new ArrayList<Cart>();
            }
            int index = -1;
            for (int i = 0; i < cs.size(); i++) { //判断商品是否存在
                Cart c = cs.get(i);
                if (c.getPid() == pid) {
                    index = i;
                    break;
                }
            }
            if (index >= 0) { //商品存在
                Cart C = cs.get(index);
                int stock = new ProductService().getProductById(C.getPid()).getStock();//得到该商品的库存
                int up = C.getQuantity() + sl;//得到商品要更改的数量
                if (up >= stock) {  //库存不足，最大值未库存
                    up = stock;
                }
                C.setQuantity(up); //更新数量
                cs.set(index, C);
            } else {  //商品不存在
                Cart c = new Cart();
                c.setPid(pid);
                c.setImage(p.getImage());
                c.setPrice(p.getPrice());
                c.setName(p.getName());
                c.setQuantity(sl);
                cs.add(c);	//添加
            }

            session.setAttribute("cart", cs);
        } else { //用户已登录
            CartService CS = new CartService();
            Cart c = CS.isExistsProduct(u.getId(), pid);
            if (c != null) {
                int stock = new ProductService().getProductById(c.getPid()).getStock();//得到该商品的库存
                int up = c.getQuantity() + sl;//得到商品要更改的数量
                if (up >= stock) {  //库存不足，最大值未库存
                    up = stock;
                }
                c.setQuantity(up); //更新数据库
                CS.upCart(c);
            } else {   //添加到数据库
                Cart c2 = new Cart();
                c2.setPid(pid);
                c2.setUid(u.getId());
                c2.setQuantity(sl);
                CS.addCart(c2);
            }
        }

        response.sendRedirect("putCart_success.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
