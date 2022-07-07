package com.lagou.edu.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author litianyi
 * @version 1.0
 */
public class LogUtils {

    public void beforeMethod(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg);
        }
        System.out.println("业务逻辑之前...");
    }

    public void afterMethod(JoinPoint joinPoint, Object result) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg);
        }
        System.out.println("业务逻辑之后..." + result);
    }

    public void exceptionMethod() {
        System.out.println("业务逻辑异常...");
    }

    public void finalMethod() {
        System.out.println("业务逻辑结束...");
    }

    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) {
        Object result;

        try {
            Object[] args = proceedingJoinPoint.getArgs();
            System.out.println("环绕通知的前置通知...");
            result = proceedingJoinPoint.proceed(args);
            System.out.println("环绕通知的后置通知...");
        } catch (Throwable e) {
            System.out.println("环绕通知的异常通知...");
            throw new RuntimeException(e);
        } finally {
            System.out.println("环绕通知的最终通知...");
        }

        return result;
    }
}
