package com.ws.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * A class implements ApplicationContextAware interface to get bean.
 */
public class SpringUtil implements ApplicationContextAware{

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        applicationContext = ac;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * The method is used to get bean by beanId from applicationContext.
     * @param beanId
     * @return Object
     */
    public static Object getBean(String beanId) {
        ApplicationContext applicationContext = getApplicationContext();
        return applicationContext.getBean(beanId);
    }
}
