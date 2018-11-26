package org.sample.webapp.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 处理中文乱码问题
 */
public class CharacterEncodingFilter implements Filter {

    private static String charset = "UTF-8"; // 默认utf-8

    @Override
    public void init(FilterConfig config) throws ServletException {
        charset = config.getInitParameter("charset");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding(charset); // 不加的话req.getParameter获取中文会乱码
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
