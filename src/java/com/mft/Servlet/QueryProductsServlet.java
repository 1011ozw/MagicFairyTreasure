/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Servlet;

import com.mft.Bean.Product;
import com.mft.Service.ProductService;
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
public class QueryProductsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs 查询商品
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int id = 0;
        int stock = 0;
        int notInId = 0;
        if (request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));
        } else if (request.getParameter("stock") != null) {
            stock = Integer.parseInt(request.getParameter("stock"));
        } else if (request.getParameter("notInId") != null) {
            notInId = Integer.parseInt(request.getParameter("notInId"));
        } else {
            System.out.println("查询所有商品");
        }
        int pageSize = 5;
        int pageIndex = 0;
        int pageSum = UtilHelper.totalPages(pageSize, UtilHelper.getCount("product"));
        if (request.getParameter("pageIndex") != null) {
            pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        }
        if (pageIndex > pageSum) {
            pageIndex = pageSum;
        }
        if (pageIndex < 0) {
            pageIndex = 0;
        }
        ProductService ps = new ProductService();
        List<Product> products = ps.show(id, stock, notInId, pageSize, pageIndex);
        request.setAttribute("products", products);
        request.setAttribute("pageSum", pageSum);
        request.setAttribute("pageIndex", pageIndex);
        if (request.getParameter("type") == null) {
            request.getRequestDispatcher("products.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("updateProducts.jsp").forward(request, response);
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
