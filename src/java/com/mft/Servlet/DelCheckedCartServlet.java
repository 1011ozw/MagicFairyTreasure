/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Servlet;

import com.mft.Bean.Cart;
import com.mft.Bean.User;
import com.mft.Service.CartService;
import java.io.IOException;
import java.io.PrintWriter;
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
public class DelCheckedCartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = (String) request.getParameter("pid");//得到单击删除时传递来的数据
        HttpSession session = request.getSession();
        List<Cart> cs = (List<Cart>) session.getAttribute("cart");//获取未登录时购物车中数据
        User u = (User) session.getAttribute("user");
        String[] pids = request.getParameterValues("checkbox");//获取传递来的数据
        if (pids == null && id != null) {//判断是单击了删除时
            pids = new String[1];
            pids[0] = id;
        }
        if (pids != null) {//必须选中复选框
            for (String s : pids) {
                if (cs != null) {
                    int index = -1;
                    for (int i = 0; i < cs.size(); i++) {     //循环找到要删除商品在集合中的下标
                        Cart c = cs.get(i);
                        if ((c.getPid() + "").equals(s)) {
                            index = i;
                            break;
                        }
                    }
                    if (index >= 0) {    //找到商品，删除
                        cs.remove(index);
                    }
                }
                if (u != null) {
                    int pid = Integer.parseInt(s);
                    new CartService().delCartByPid(pid, u.getId());
                }
            }
            session.setAttribute("cart", cs); //将集合放回
        }
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
