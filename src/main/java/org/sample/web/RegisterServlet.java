package org.sample.web;

import org.sample.dto.OnesProfileResult;
import org.sample.service.ProfileService;
import org.sample.service.impl.ProfileServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    private final ProfileService profileService = ProfileServiceImpl.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); // 不加这句req.getParameter中文乱码
        resp.setCharacterEncoding("UTF-8"); // 不加这句输出中文会乱码！

        // data
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String nickname = req.getParameter("nickname");
        System.out.println(username);
        System.out.println(password);
        System.out.println(nickname);

        // process
        OnesProfileResult result = profileService.register(username, password, nickname);
        System.out.println(result.getExtraInfo().getStateInfo());

        // return
        req.setAttribute("result", result);
        req.getRequestDispatcher("WEB-INF/views/profile_save.jsp").forward(req, resp);
        // 为JSP实例化一个请求分派器，使用请求分派器要求容器准备好JSP，并向JSP发送请求和响应
    }
}
