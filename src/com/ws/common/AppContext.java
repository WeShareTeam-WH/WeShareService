package com.ws.common;

import java.util.HashMap;
import java.util.Map;

import com.ws.Constant;
import com.ws.model.User;


/**
 * The class is used to page the data with current thread,
 * such as connection object in a request.
 * */
public class AppContext {

    private static ThreadLocal<AppContext> threadLocal = new ThreadLocal<AppContext>();
    private Map<String, Object> contextData = new HashMap<String, Object>();
    private static String contextPath;

    private AppContext() {
        //Empty block
    }

    /**
     * The method is used to get appContext object by current thread.
     * @return AppContext object.
     * */
    public static AppContext getContext() {
        AppContext appContext = threadLocal.get();
        if (appContext == null) {
            appContext = new AppContext();
            threadLocal.set(appContext);
        }
        return appContext;
    }

    public static String getContextPath() {
        return contextPath;
    }

    public static void setContextPath(String contextPath) {
        if (AppContext.contextPath == null) {
            AppContext.contextPath = contextPath;
        }
    }

    public Object getObject(String key) {
        return contextData.get(key);
    }

    public void addObject (String key, Object object) {
        contextData.put(key, object);
    }

    public Map<String, Object> getContextData() {
        return contextData;
    }

    public void setContextData(Map<String, Object> contextData) {
        this.contextData = contextData;
    }

    public void removeObject(String key) {
        contextData.remove(key);
    }

    public User getUser() {
        return (User)contextData.get(Constant.USER);
    }

    public String getUserName() {
        User user = (User)contextData.get(Constant.USER);
        if (user != null) {
            String name = user.getName();
            return name;
        }
        return Constant.BLANK;
    }

    public Integer getUserId() {
        User user = (User)contextData.get(Constant.USER);
        if (user != null) {
            Integer id = user.getId();
            return id;
        }
        return null;
    }

    public void clear() {
        AppContext context = threadLocal.get();
        if (context != null) {
            context.contextData.clear();
        }
        context = null;
    }
}
