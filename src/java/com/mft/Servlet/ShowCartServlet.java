/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Servlet;

import com.mft.Bean.Cart;
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

/**
 *
 * @author 张帆
 */
public class ShowCartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs 显示购物车
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        User u = (User) request.getSession().getAttribute("user");
        List<Cart> ls = (List<Cart>) request.getSession().getAttribute("cart");
        List<String> stocks = new ArrayList<String>(); //存放各个商品的库存
        ProductService ps = new ProductService();
        if (u == null) { //用户未登录
            if (ls != null) {
                for (int i = 0; i < ls.size(); i++) {
                    Cart c = ls.get(i);
                    int pid = c.getPid();
                    int stock = ps.getProductById(pid).getStock();
                    stocks.add(stock + "");
                }
                request.getSession().setAttribute("stocks", stocks);
            }
            request.getSession().setAttribute("carts", ls);
            request.getSession().setAttribute("url", "ShowCartServlet");
        } else { //用户已登录
            CartService cs = new CartService();
            List<Cart> carts = cs.getCartsByUid(u.getId());
            if (carts != null) {
                for (int i = 0; i < carts.size(); i++) {
                    Cart c = carts.get(i);
                    int pid = c.getPid();
                    int stock = ps.getProductById(pid).getStock();
                    stocks.add(stock + "");
                }
                request.getSession().setAttribute("stocks", stocks);
            }
            request.getSession().setAttribute("carts", carts);
        }
        request.getRequestDispatcher("myCart.jsp").forward(request, response);
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
