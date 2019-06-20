/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Servlet;

import com.mft.Bean.Order;
import com.mft.Service.OrderService;
import com.mft.Util.UtilHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 张帆
 */
public class QueryAllOrderServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs 查看订单
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        OrderService os = new OrderService();
        String types = request.getParameter("type");
        if (types == null) {
            types = "0";
        }
        int type = Integer.parseInt(types);
        int pageSize = 5;
        int pageIndex = 0;
        if (request.getParameter("pageIndex") != null) {
            pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        }
        int count = 0;
        if (type == 0) {
            count = UtilHelper.getCount("order");
        } else if (type == 1) {
            count = new OrderService().getCountOrdersByStatus(0);
        } else if (type == 2) {
            count = new OrderService().getCountOrdersByStatus(1);
        } else if (type == 3) {
            count = new OrderService().getCountOrdersByStatus(2);
        } else {
            count = new OrderService().getCountOrdersByStatus(3);
        }
        int pageSum = UtilHelper.totalPages(pageSize, count);
        if (pageIndex > pageSum) {
            pageIndex = pageSum;
        }
        if (pageIndex < 0) {
            pageIndex = 0;
        }
        request.setAttribute("pageIndex", pageIndex);
        request.getSession().setAttribute("pageSum", pageSum);
        List<Order> orders = os.QueryAllOrder(type, pageIndex, pageSize);
        request.getSession().setAttribute("orders", orders);
        request.getSession().setAttribute("type", type);
        request.getRequestDispatcher("orders.jsp").forward(request, response);
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
