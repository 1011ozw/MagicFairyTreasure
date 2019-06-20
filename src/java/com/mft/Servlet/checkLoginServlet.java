/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Servlet;

import com.mft.Bean.User;
import com.mft.Service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 张帆
 */
public class checkLoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *  登录验证
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        int count = Integer.parseInt(session.getAttribute("count") + "");//得到登录次数
        count++;
        if (count == 9) {
            count = 4;
        }
        session.setAttribute("count", count);
        out.print(count);
        String name = request.getParameter("username");//得到输入的用户名
        String pwd = request.getParameter("pwd");//得到输入的密码
        String yan = request.getParameter("yan");//得到输入的验证码
        String rand = (String) session.getAttribute("rand");//得到生成的验证码
        if (yan.equals("88888888")) {  //当页面中未显示验证码时
            yan = "100";
            rand = "100";
        }
        UserService us = new UserService();
        User u = us.checkLogin(name, pwd);//验证用户
        String url = (String) session.getAttribute("url");
        if (url == null) {
            url = "index.jsp";
            session.setAttribute("url", url);
        }
        if (!yan.equals(rand)) { //验证码输入错误
            out.print("yanerror");
            return;
        }
        if (!us.isUserByName(name)) {//用户名不存在错误
            out.print("nameerror");
            return;
        }
        if (u == null) {   //密码输入错误
            //response.sendRedirect("login.jsp");
            out.print("pwderror");
            return;
        } else {
            if (yan.equals(rand)) {
                if (u.getStatus() == 1) {  //普通用户登录
                    session.setAttribute("user", u);
                    //request.getRequestDispatcher(url).forward(request, response);
                    //response.sendRedirect(url);
                    out.print("usersuccess");
                } else {
                    //session.setAttribute("user", u);
                    //request.getRequestDispatcher("login.jsp").forward(request, response);
                    out.print("adminsuccess");
                }
            }
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
