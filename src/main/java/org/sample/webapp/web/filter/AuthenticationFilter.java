package org.sample.webapp.web.filter;

import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 权限控制
 */
public class AuthenticationFilter implements Filter {

    private static final String[] ACCESSIBLE_PAGE = {"/test/register.html", "/test/register.do"};
    private static final String INACCESSIBLE_WARNING = "你没有权限访问这个页面，或者。。这个页面还没写好！";

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        final String uri = httpReq.getRequestURI();
        if (ArrayUtils.contains(ACCESSIBLE_PAGE, uri)) {
            chain.doFilter(req, resp);
        } else {
            //设置返回内容类型
            resp.setContentType("text/html;charset=UTF-8");
            //在页面输出响应信息
            PrintWriter out = resp.getWriter();
            out.print(INACCESSIBLE_WARNING);
        }
    }

    @Override
    public void destroy() {

    }
}
