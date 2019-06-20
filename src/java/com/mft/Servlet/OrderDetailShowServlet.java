/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Servlet;

import com.mft.Bean.Order;
import com.mft.Bean.OrderDetail;
import com.mft.Service.OrderDetailService;
import com.mft.Service.OrderService;
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
public class OrderDetailShowServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs 显示订单明细
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int oid = Integer.parseInt(request.getParameter("oid"));//获取订单编号
        OrderDetailService ods = new OrderDetailService();
        List<OrderDetail> os = ods.getOrderDetailsByOid(oid);//根据订单编号得到订单明细
        OrderService OS = new OrderService();
        Order o = OS.getOrderByOid(oid);
        String status = "";//状态
        if (o.getStatus() == 0) {
            status = "待发货";
        } else if (o.getStatus() == 1) {
            status = "已发货";
        } else if (o.getStatus() == 2) {
            status = "交易成功";
        } else {
            status = "已取消";
        }
        request.setAttribute("status", status);
        request.setAttribute("OrderDetails", os);
        request.setAttribute("order", o);
        request.getRequestDispatcher("orderDetail.jsp").forward(request, response);
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
