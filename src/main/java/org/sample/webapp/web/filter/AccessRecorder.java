package org.sample.webapp.web.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.webapp.util.IpAddressUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * TODO 点击量记录、防止流量攻击等。。
 * 记录访问者的ip以及服务端程序从
 * 处理请求到返回响应的所花费的时
 * 间等相关信息。
 */
public class AccessRecorder implements Filter {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        final String ipAddress = IpAddressUtil.getIpAdrress(httpReq);
        final String method = httpReq.getMethod();
        final String uri = httpReq.getRequestURI();
        final long start = System.currentTimeMillis();
        // TODO 异步日志
        chain.doFilter(req, resp);
        final long end = System.currentTimeMillis();
        LOGGER.debug("{} {} {} {}ms", ipAddress, method, uri, end - start); // 时间，ip地址，做了啥，结果怎么样
    }

    @Override
    public void destroy() {

    }
}
