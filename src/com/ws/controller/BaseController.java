package com.ws.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ws.Constant;
import com.ws.common.AppContext;
import com.ws.model.User;
import com.ws.util.SessionUtil;

/**
 * Base controller abstract class,
 * Provide user,session,RedirectView methods.
 */
public abstract class BaseController {

    private final Logger logger = Logger.getLogger(BaseController.class);

    /**
     * The method is  used to log message and send to server error page.
     * @param e
     * @return ModelAndView object.
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        logger.error(e.getMessage(), e);
        ModelAndView modelAndView = new ModelAndView(this.getRedirectView(Constant.ERROR_PAGE));
        return modelAndView;
    }

    /**
     * The method is used to get RedirectView.
     * @param path
     * @return RedirectView
     */
    protected RedirectView getRedirectView(String path) {
        return new RedirectView(AppContext.getContextPath() + Constant.SLASH + path);
    }

    /**
     * The method is used to get user from AppContext.
     * @return User
     */
    protected User getUser() {
        return AppContext.getContext().getUser();
    }

    /**
     * The method is used to get user name.
     * @return user name.
     */
    public String gerUserName() {
        User user = getUser();
        if (user != null) {
            return user.getName();
        }
        return "";
    }

    /**
     * The method is used to get userId.
     * @return Integer
     */
    public int getUserId() {
        User user = getUser();
        if (user != null) {
            return user.getId();
        }
        return 0;
    }

    /**
     * The method is used to add object to session.
     * @param key
     * @param object
     */
    protected void addSession(String key, Object object) {
        SessionUtil.addSession(key, object);
    }

    /**
     * The method is used to get session by key.
     * @param key
     */
    protected Object getSession(String key) {
        return SessionUtil.getSession(key);
    }

    /**
     * The method is used to remove session by key.
     * @param key
     */
    protected void removeSession(String key) {
        SessionUtil.removeSession(key);
    }

    /**
     * Invalidate session
     */
    protected void invalidate() {
        SessionUtil.invalidate();
    }
}
