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
public class GetProductsServlet extends HttpServlet {

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
        String Pcid = request.getParameter("pcid");
        String Ppid = request.getParameter("ppid"); //得到分类信息
        if (Pcid == null) {
            Pcid = "1";
        }
        if (Ppid == null) {
            Ppid = "1";
        }
        int pcid = Integer.parseInt(Pcid);
        int ppid = Integer.parseInt(Ppid);
        String str = request.getParameter("str");//得到要查询的条件
        if (str == null) {
            //str="不限,不限,不限,不限,不限,";
            str = (String) request.getSession().getAttribute("str");
        } else {
            str = new String(str.getBytes("ISO-8859-1"), "utf-8");
            request.getSession().setAttribute("str", str);
        }
        System.out.println(str);
        String[] s = str.split(",");
        String name = s[0];
        if (name.equals("不限")) {
            name = "";
        }
        String price = "不限";
        if (s.length > 1) {
            price = s[1];
        }
        if (!price.equals("不限")) {
            String a = s[1].split("-")[0];
            String b = s[1].split("-")[1];
            price = "and price >=" + a + " and price <=" + b;
        } else {
            price = "and price>0";
        }
        String size = "";
        if (s.length > 2) {
            size = s[2];
        }
        String terrace = "";
        if (s.length > 3) {
            terrace = s[4];
        }
        String videocard = "";
        if (s.length > 3) {
            videocard = s[4];
        }

        int pageSize = 8;
        String pageIndex = request.getParameter("pageIndex");
        if (pageIndex == null) {
            pageIndex = "1";
        }
        int currentPage = Integer.parseInt(pageIndex); //当前页
        ProductService ps = new ProductService();
        int count = ps.getCount(ppid, pcid, terrace, price, size, "", videocard, name);//商品总数
        int totalPages = UtilHelper.totalPages(pageSize, count);//总页数
        List<Product> products = ps.getProductsByPage(currentPage, pageSize, ppid, pcid, terrace, price, size, "", videocard, name);
        int begin = (currentPage / 3) * 3 + 1;//分页索引
        if (ppid != 1 || pcid != 1) { //无条件查询商品
            products = ps.getProductsByPage(currentPage, pageSize, ppid, pcid);
            count = ps.countProducts(pcid, ppid);
            totalPages = UtilHelper.totalPages(pageSize, count);
        }
        //分别放入变量中
        request.setAttribute("ppid", ppid);
        request.setAttribute("pcid", pcid);
        request.setAttribute("begin", begin);
        request.setAttribute("products", products);
        request.setAttribute("count", count);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        request.getRequestDispatcher("showProducts_result.jsp").forward(request, response);
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
