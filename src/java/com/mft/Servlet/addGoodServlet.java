/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Servlet;

import com.mft.Bean.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import com.mft.Service.ProductService;
import javax.servlet.jsp.JspFactory;
import org.lxh.smart.Request;
import org.lxh.smart.SmartUpload;
/**
 *
 * @author 张帆
 */
public class addGoodServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        
        System.out.println("Servlet");
        PrintWriter out = response.getWriter();
        Product p = new Product();
        //实例化上传组件
        SmartUpload su = new SmartUpload();
        //初始化上传操作
        //初始化pageContext
        PageContext pageContext = null;
        try {
            pageContext = _jspxFactory.getPageContext(this, request, response, null, true, 8192, true);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        su.initialize(pageContext);
        //设置字符集
        //su.setCharset("utf-8");
        //设置上传限制文件格式
        su.setAllowedFilesList("jpg");
        //准备上传
        try {
            su.upload();
        } catch (Exception e) {
            // TODO: 异常处理
            e.printStackTrace();
        }
        //实例化request对象
        Request requ = su.getRequest();
        //获取页面输入的价格
        String name = requ.getParameter("price");
        System.out.println(name);
        //判断如果获取到的商品名称不为空时
        if (requ.getParameter("GoodName") != null) {
            //遍历得到商品的每一个属性对象值
            p.setName("'" + requ.getParameter("GoodName") + "'");
            p.setDescription("'" + requ.getParameter("description") + "'");
            p.setPrice(requ.getParameter("price"));
            p.setStock(Integer.parseInt(requ.getParameter("stock")));
            p.setPpid(Integer.parseInt(requ.getParameter("ppid")));
            p.setPcid(Integer.parseInt(requ.getParameter("pcid")));
            String filename = "'file/" + su.getFiles().getFile(0).getFileName() + "'";
            p.setImage(filename);
            System.out.println("文件名---" + p.getImage());

        }
        //声明一个商品的尺寸大小size
        String size = requ.getParameter("size");
        System.out.println("--" + size + "--");
        //判断如果得到商品的尺寸大小不为一个空的字符串
        if (!requ.getParameter("size").equals("")) {
            //遍历获取到属性值
            p.setSize(requ.getParameter("size"));
            p.setTerrace(requ.getParameter("tarrace"));
            p.setHardpan(requ.getParameter("hardpan"));
            p.setVideocard(requ.getParameter("videocard"));
        }
        //准备上传
        try {
            //创建ProductService对象
            ProductService ps = new ProductService();
            //声明一个int型的r变量，并将其添加的对象传递在r变量中
            int r = ps.addGood(p);
            //判断如果变量r大于0的时间，说明商品添加成功
            if (r > 0) {
                //上传商品
                su.save("file");
                //提示后台管理员上传成功
                System.out.println("上传成功！！");
                //然后追加上传成功是否继续上传，如果点击confirm框的确定按钮时，将继续上传商品，点击取消，将跳转到商品列表页面
                out.print("<script>if(confirm(" + "'上传成功是否继续上传?'" + ")){\nsetTimeout(" + "location.href=" + "'showOneServlet'" + ",500);}else{"
                        + "\nalert(" + "'正在跳转商品列表页面...'" + ")}</script>");
            } else {
                //否则输出上传失败
                System.out.println("上传失败！！");
                //跳转到商品的增加页面
                out.print("<script>alert('上传失败正在跳转至增加商品页面...');\n"
                        + "setTimeout(" + "location.href=" + "'showOneServlet'" + ",500);</script>");
            }
        } catch (Exception e) {
            // TODO: 异常处理
            e.printStackTrace();
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
