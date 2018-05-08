package com.ws.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.ws.Constant;

/**
 * The filter is used to control the encoding format.
 * */
public class PageEncodingFilter implements Filter {

    private String encoding = "UTF-8";

    /**
     *The method is used to dealing with data garbled.
     *@param ServletRequest object.
     *@param ServletResponse object.
     *@param FilterChain object.
     * */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding(encoding);
        filterChain.doFilter(request, servletResponse);
    }

    /**
     *The method is used to get encoding data from web.XML file.
     *@param FilterConfig object.
     * */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (filterConfig.getInitParameter(Constant.ENCODING) != null) {
            encoding = filterConfig.getInitParameter(Constant.ENCODING);
        }
    }
    /**
     * The method is used in filter destroy.
     * */
    @Override
    public void destroy() {
        //Empty block
    }
}
