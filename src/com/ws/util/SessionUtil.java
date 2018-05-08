package com.ws.util;

import java.lang.reflect.Method;

import com.ws.Constant;
import com.ws.common.AppContext;

/**
 * SessionUtil class to manager session.
 */
public class SessionUtil {

    private static Object getSessionInThread() {
        Object session = AppContext.getContext().getObject(Constant.APP_CONTEXT_SESSION);
        return session;
    }

    public static void addSession(String key, Object object) {
        Object session = getSessionInThread();
        if (session == null) {
            return;
        }
        try {
            //Define a param array with two params
            Class<?>[] params = new Class[2];
            params[0] = String.class;
            params[1] = Object.class;
            Method method = session.getClass().getMethod(Constant.SET_ATTRIBUTE, params);
            Object[] objects = new Object[] {key, object};
            method.invoke(session, objects);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getSession(String key) {
        Object session = getSessionInThread();
        if (session == null) {
            return null;
        }
        try {
            //Define a param array with one param
            Class<?>[] params = new Class[1];
            params[0] = String.class;
            Method method = session.getClass().getMethod(Constant.GET_ATTRIBUTE, params);
            Object[] objects = new Object[] {key};
            Object returnValue = method.invoke(session, objects);
            return returnValue;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeSession(String key) {
        Object session = getSessionInThread();
        if (session == null) {
            return;
        }
        try {
            //Define a param array with one param
            Class<?>[] params = new Class[1];
            params[0] = String.class;
            Method method = session.getClass().getMethod(Constant.REMOVE_ATTRIBUTE, params);
            Object[] objects = new Object[] {key};
            method.invoke(session, objects);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void invalidate() {
        Object session = getSessionInThread();
        if (session == null) {
            return;
        }
        try {
            Method method = session.getClass().getMethod(Constant.INVALIDATE);
            method.invoke(session);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
