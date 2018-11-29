package org.sample.webapp.web.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.webapp.dto.OPResult;
import org.sample.webapp.service.ProfileService;
import org.sample.webapp.service.impl.ProfileServiceImpl;
import org.sample.webapp.util.IpAddressUtil;

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

    private static final Logger LOGGER = LogManager.getLogger();
    private final ProfileService profileService = ProfileServiceImpl.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取前台POST参数
        final String ip =  IpAddressUtil.getIpAdrress(req);
        final String username = req.getParameter("username");
        final String password = req.getParameter("password");
        final String nickname = req.getParameter("nickname");

        // 处理
        LOGGER.info("{} - registration began: registration{username={}, password={}, nickname={}}", ip, username, password, nickname);
        OPResult result = profileService.register(username, password, nickname);
        LOGGER.info("{} - end of registration: {}", ip, result);

        // 返回结果
        req.setAttribute("result", result);
        req.getRequestDispatcher("WEB-INF/views/profile_save.jsp").forward(req, resp);
        // 为JSP实例化一个请求分派器，使用请求分派器要求容器准备好JSP，并向JSP发送请求和响应
    }
}
