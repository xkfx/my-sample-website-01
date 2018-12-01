package org.sample.webapp.web.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class AccessRecorder implements Filter {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        /**
         * 诸如访问者ip、访问的uri等都可以通过tomcat默认日志直接
         * 拿到，而网站的流量、响应时间等也可以通过分析tomcat日志拿到，
         * 所以应该在这里执行一些应用相关的代码。
         */
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
