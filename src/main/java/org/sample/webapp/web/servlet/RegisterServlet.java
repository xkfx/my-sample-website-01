package org.sample.webapp.web.servlet;

import org.sample.webapp.dto.OPResult;
import org.sample.webapp.service.ProfileService;
import org.sample.webapp.service.impl.ProfileServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
    urlPatterns = {"/register.do", "/bar", "/cool"}
)
public class RegisterServlet extends HttpServlet {

    private final ProfileService profileService = ProfileServiceImpl.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取前台POST参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String nickname = req.getParameter("nickname");

        // 处理
        OPResult result = profileService.register(username, password, nickname);

        // 返回结果
        req.setAttribute("result", result);
        req.getRequestDispatcher("WEB-INF/views/profile_save.jsp").forward(req, resp);
        // 为JSP实例化一个请求分派器，使用请求分派器要求容器准备好JSP，并向JSP发送请求和响应
    }
}
