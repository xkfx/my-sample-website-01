package org.sample.webapp.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.webapp.dto.ServiceResult;
import org.sample.webapp.service.ProfileService;
import org.sample.webapp.service.impl.ProfileServiceImpl;
import org.sample.webapp.util.IpAddressUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ProfileController extends HttpServlet {

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
        ServiceResult serviceResult = profileService.register(username, password, nickname);
        LOGGER.info("{} - end of registration: {}", ip, serviceResult);
        Object result;
        if (serviceResult.isSuccess()) {
            result = serviceResult.getData();
        } else {
            result = serviceResult;
        }

        // 返回结果
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String jsonString = new ObjectMapper().writeValueAsString(result);
        PrintWriter out = resp.getWriter();
        out.print(jsonString);
        out.flush();
    }
}
