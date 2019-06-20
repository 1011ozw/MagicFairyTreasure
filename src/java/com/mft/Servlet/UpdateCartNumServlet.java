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
public class UpdateCartNumServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 更新购物车商品数量
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int pid=Integer.parseInt(request.getParameter("pid"));
		int count=Integer.parseInt(request.getParameter("num"));
		HttpSession session=request.getSession();
		User u=(User)session.getAttribute("user");	
		if(u==null){//用户未登录
			List<Cart>cs=(List<Cart>)session.getAttribute("cart");
				int index=-1;
				for(int i=0;i<cs.size();i++){
					Cart c=cs.get(i);
					if(c.getPid()==pid){
						index=i;
					}
				}
				if(index>=0){
					Cart c=cs.get(index);
					c.setQuantity(count);
					cs.set(index, c);
				}
				session.setAttribute("cart", cs);
		}else{//用户已登录
			Cart c=new Cart();
			c.setUid(u.getId());
			c.setPid(pid);
			c.setQuantity(count);
			new CartService().upCart(c);
		}
		//request.getRequestDispatcher("ShowCartServlet").forward(request, response);
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
