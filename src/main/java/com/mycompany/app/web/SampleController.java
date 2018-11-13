package com.mycompany.app.web;

import com.mycompany.app.service.impl.SampleServiceImpl;
import com.mycompany.app.service.SampleService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SampleController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1
        String color = req.getParameter("color");
        // 2
        SampleService sampleService = new SampleServiceImpl();
        List result = sampleService.getBrands(color);
        // 3
        req.setAttribute("styles", result); // 为请求增加一个属性，供JSP使用。
        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/views/result.jsp"); // 为JSP实例化一个请求分派器
        view.forward(req, resp); // 使用请求分派器要求容器准备好JSP，并向JSP发送请求和响应
    }
}