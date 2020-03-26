package com.google.filter;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;
@Configuration
public class Myfileter implements Filter {
    @Bean
    public FilterRegistrationBean<Myfileter> getMyFilter(){
        FilterRegistrationBean<Myfileter> frb=new FilterRegistrationBean<>();
        frb.setFilter(new Myfileter());
        frb.addUrlPatterns("/*");
        return frb;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
