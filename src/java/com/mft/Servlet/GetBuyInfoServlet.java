/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Servlet;

import com.mft.Bean.Cart;
import com.mft.Bean.Province;
import com.mft.Bean.User;
import com.mft.Service.ProvinceService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 *
 * @author 张帆
 */
public class GetBuyInfoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 得到购买的商品信息
     */
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<Province> ps = new ProvinceService().getProvinces(); //获取所有省份
        request.getSession().setAttribute("provinces", ps);
        String[] pids = request.getParameterValues("checkbox"); //得到选中商品的编号
        HttpSession session = request.getSession();
        List<Cart> cs = (List<Cart>) session.getAttribute("carts");
        List<Cart> BuyCarts = new ArrayList<Cart>();
        float sum = 0; //存放购买的总金额
        int count = 0; //存放购买商品的总数量
        if (pids != null && cs != null) {
            for (int i = 0; i < pids.length; i++) {
                int pid = Integer.parseInt(pids[i]);
                int index = -1;
                for (int j = 0; j < cs.size(); j++) {
                    Cart c = cs.get(j);
                    if (c.getPid() == pid) {
                        index = j;
                        break;
                    }
                }
                Cart c = cs.get(index);
                BuyCarts.add(c);
                count += c.getQuantity();
                float price = Float.parseFloat(c.getPrice());
                sum += price * c.getQuantity();
                BigDecimal b = new BigDecimal(sum);
                sum = (float) b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); //小数点控制
            }
            session.setAttribute("count", count);
            session.setAttribute("sum", sum);
            session.setAttribute("BuyCarts", BuyCarts);
            User u = (User) session.getAttribute("user");
            if (u == null) { //用户登录时
                session.setAttribute("url", "user/orderInfo.jsp"); //存入路径信息
                response.sendRedirect("login.jsp");
            } else {
                request.getRequestDispatcher("user/orderInfo.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("myCart.jsp");
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
