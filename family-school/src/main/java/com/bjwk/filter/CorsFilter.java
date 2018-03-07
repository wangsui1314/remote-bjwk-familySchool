package com.bjwk.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;



/**
 * Created by XiaoJiang01 on 2017/3/17.
 */

public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,Authorization,Content-Type,token,admintoken,saletoken,date,Content-Encoding,server,connection,transfer-encoding");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Content-Type","application/json;charset=UTF-8,application/gzip");
        filterChain.doFilter(servletRequest,response);
        /**
         *  HttpServletResponse response = (HttpServletResponse) res;  
            response.setHeader("Access-Control-Allow-Origin", "*");  
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
            response.setHeader("Access-Control-Max-Age", "3600");  
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");  
            chain.doFilter(req, res);  
         */
    }

    @Override
    public void destroy() {

    }
}
