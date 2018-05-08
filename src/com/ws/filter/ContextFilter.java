package com.ws.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ws.Constant;
import com.ws.common.AppContext;
import com.ws.model.User;
/**
 * The filter is used to block all action.
 * and create connection object for each request.
 * */
public class ContextFilter implements Filter {

    /**
     *The method is used to create an connection when get a request.
     *@param ServletRequest object.
     *@param ServletResponse object.
     *@param FilterChain object.
     * */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        AppContext.setContextPath(request.getContextPath());
        String uri = request.getRequestURI();
        String requestedUri = uri.substring(request.getContextPath().length() + 1);
        if(requestedUri.endsWith(Constant.CSS) || requestedUri.endsWith(Constant.JSS) || requestedUri.endsWith(Constant.PNG) || requestedUri.endsWith(Constant.JPG)) {
            filterChain.doFilter(request, response);
            return;
        }
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Constant.USER);
        AppContext.getContext().addObject(Constant.USER, user);
        AppContext.getContext().addObject(Constant.APP_CONTEXT_SESSION, session);
        try {
            filterChain.doFilter(request, response);
        } finally {
            AppContext.getContext().clear();
        }
    }
    /**
     *The method is used to get the data from XML file.
     *@param FilterConfig object.
     * */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Empty block
    }

    /**
     * The method is used in filter destroy.
     * */
    @Override
    public void destroy() {
        //Empty block
    }
}
