package com.manager.javawebsemesterhomework.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: Aze
 * @Description: TODO
 * @Date: 2023/12/31 21:00
 */

@Component
@Order(1)
@Slf4j
public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 判断请求路径是否为 /login 如果是则放行。如果不是则判断session中是否有登录信息，如果有则放行，如果没有则跳转到登录页面
        String url = ((HttpServletRequest) servletRequest).getRequestURI();
//        log.info("url: " + url);

        if(!(url.equals("/login") || url.startsWith("/js") || url.startsWith("/css") || url.startsWith("/images") || url.startsWith("/fonts") || url.startsWith("/checkLogin") || url.startsWith("/captcha") )) {
            String username = (String) ((HttpServletRequest) servletRequest).getSession().getAttribute("username");
            if (username == null) {
                ((HttpServletResponse) servletResponse).sendRedirect("/login");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
