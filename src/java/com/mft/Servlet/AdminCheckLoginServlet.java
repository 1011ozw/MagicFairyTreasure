/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Servlet;

import com.mft.Bean.User;
import com.mft.Service.UserService;
import com.mft.Util.UtilHelper;
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
public class AdminCheckLoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs 后台登录验证
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //获取输入的用户名
        String userName = request.getParameter("userName");
        //获取密码
        String userPwd = request.getParameter("userPwd");
        //获取用户类型
        int status = Integer.parseInt(request.getParameter("status"));
        //实例化用户Service模型
        UserService us = new UserService();
        //进行身份验证
        User u = us.login(userName, userPwd, status);
        //实例化一个session会话对象
        HttpSession session = request.getSession();
        //session
        session.setAttribute("users" + session.getId(), u);
        //判断用户不为空并且用户的状态为0的时间
        if (u != null && u.getStatus() == 0) {
            //控制台输出为true
            out.print("true");
            //声明一个time变量，用来保存系统当前时间
            String time = UtilHelper.getNowTime();
            //得到session会话的时间
            session.setAttribute("time", time);
            //判断如果用户不为空的时间并且状态值为1的时间
        } else if (u != null && u.getStatus() == 1) {
            //控制台输出true
            out.print("true");
            //保存系统当前时间
            String time = UtilHelper.getNowTime();
            //得到session会话时间
            session.setAttribute("time", time);
        } else {
            //否则输出false
            out.print("false");
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
