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
import com.ws.util.StringUtil;

/**
 * The filter deal with the session failure,
 * If session failure,the method will remember the address,
 * If session no failure,the method will remember the state of user login.
 * */
public class SessionFilter implements Filter {

    private String needLoginPages;

    /**
     *The method is used to dealing with session failure.
     *@param ServletRequest object.
     *@param ServletResponse object.
     *@param FilterChain object.
     * */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        String requestedUri = uri.substring(request.getContextPath().length() + 1);
        String [] pages = needLoginPages.split(",");
        Boolean isNotAllow = Boolean.FALSE;
        if(requestedUri.endsWith(Constant.CSS) || requestedUri.endsWith(Constant.JSS) || requestedUri.endsWith(Constant.PNG) || requestedUri.endsWith(Constant.JPG)) {
            filterChain.doFilter(request, response);
            return;
        }
        for(String page : pages) {
            if(page.equals(requestedUri)) {
            	isNotAllow = Boolean.TRUE;
                break;
            }
        }
        if (isNotAllow) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("USER");
            if (user == null) {
            	if(request.getMethod().toLowerCase().equals("get")) {
            		String queryString = request.getQueryString();
            		String go = requestedUri;
            		if (!StringUtil.isEmpty(queryString)) {
            			go = go + "#" + request.getQueryString();
            		}
            		session.setAttribute("hiddens", 1);
            		response.sendRedirect(AppContext.getContextPath() + "/" + "page/home/init-data?go="+go);
            	} else {
            		response.sendRedirect(AppContext.getContextPath() + "/" + "page/home/init-data");
            	}
            } else {
            	filterChain.doFilter(request, response);
            }
        } else {
        	filterChain.doFilter(request, response);
        }
    }

    /**
     *The method is used to get parameter data from web.XML file.
     *@param FilterConfig object.
     * */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if(filterConfig.getInitParameter("needLoginPages") != null) {
        	needLoginPages = filterConfig.getInitParameter("needLoginPages");
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
