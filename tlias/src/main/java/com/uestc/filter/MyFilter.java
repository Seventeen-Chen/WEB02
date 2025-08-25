package com.uestc.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("我进来了啦啦啦啦啦啦");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器执行了啦啦啦啦啦啦");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("过滤器又执行完了啦啦啦啦啦啦");
    }

    @Override
    public void destroy() {
        System.out.println("过滤器被销毁了啦啦啦啦啦啦");
        Filter.super.destroy();
    }
}
