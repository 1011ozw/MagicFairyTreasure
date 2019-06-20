/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Servlet;

import com.mft.Bean.Cart;
import com.mft.Bean.Order;
import com.mft.Bean.OrderDetail;
import com.mft.Bean.User;
import com.mft.Service.CartService;
import com.mft.Service.OrderDetailService;
import com.mft.Service.OrderService;
import com.mft.Util.UtilHelper;
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
public class AddOrderServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 添加订单
     */
    
    private static final long serialVersionUID = 1L;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name=request.getParameter("uname");//获取收货人名字
		String address=request.getParameter("address");//获取收货人地址
		String phone=(request.getParameter("phone")==null)?request.getParameter("phone2"):request.getParameter("phone");//获取手机号或电话号
		String email=request.getParameter("email");//获取收货人email
		int type=Integer.parseInt(request.getParameter("radio"));//获取付款类型
		String pr=request.getParameter("fenji");//获取选中级联的地址
		String time=UtilHelper.getNowTime();//获取当前时间
		HttpSession session=request.getSession();
		float sum=(Float)session.getAttribute("sum");//或取订单总金额
		User u=(User)session.getAttribute("user");//获取用户信息
		List<Cart>cs=(List<Cart>)session.getAttribute("BuyCarts");//获取要购买的商品信息
		Order o=new Order(0, u.getId(), name, pr+address, time, sum+"", 0, type,phone,email);//生成一个订单实体类
		OrderService os=new OrderService();
		int r=os.addOrder(o);//添加订单
		if(r>0){    //添加成功时
			int oid=os.getOid();
			for(int i=0;i<cs.size();i++){//循环添加订单明细信息，并删除购物车
				Cart c=cs.get(i);
				float cost=Float.parseFloat(c.getPrice())*c.getQuantity();
				OrderDetail od=new OrderDetail(0, oid, c.getPid(), c.getQuantity(), cost+"");
				OrderDetailService ods=new OrderDetailService();
				ods.addOrderDetail(od); //添加订单明细
				CartService CS=new CartService();
				CS.delCartByPid(c.getPid(), u.getId()); //删除购物车数据
			}
			session.removeAttribute("sum"); //移除该变量
			session.removeAttribute("count"); //移除该变量
			response.sendRedirect("user/order_success.jsp"); //跳转到订单提交成功页面
		}else{
			response.sendRedirect("user/orderInfo.jsp");  //跳转到填写收货人信息页面
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
