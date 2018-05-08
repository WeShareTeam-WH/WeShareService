package com.ws.service.impl;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

import com.ws.Constant;
import com.ws.common.AppContext;

/**
 * A class to log info and error message in runtime
 */
public class LogMethodTime implements MethodInterceptor{

    private final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object returnValue = methodInvocation.proceed();
        String methodName = methodInvocation.getMethod().getName();
        long endTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append(AppContext.getContext().getUserName());
        sb.append(Constant.COLON);
        sb.append(methodInvocation.getMethod().getDeclaringClass().getSimpleName());
        sb.append(Constant.COLON);
        sb.append(methodName);
        sb.append(Constant.COLON);
        sb.append(endTime-startTime);
        logger.info(sb.toString());
        return returnValue;
    }

}
