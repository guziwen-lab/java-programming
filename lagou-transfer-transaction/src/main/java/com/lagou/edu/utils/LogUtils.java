package com.lagou.edu.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author litianyi
 * @version 1.0
 */
@Component
@Aspect
public class LogUtils {

    @Pointcut("execution(* com.lagou.edu.service..*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void beforeMethod(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg);
        }
        System.out.println("业务逻辑之前...");
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterMethod(JoinPoint joinPoint, Object result) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg);
        }
        System.out.println("业务逻辑之后..." + result);
    }

    @AfterThrowing("pointCut()")
    public void exceptionMethod() {
        System.out.println("业务逻辑异常...");
    }

    @After("pointCut()")
    public void finalMethod() {
        System.out.println("业务逻辑结束...");
    }

    @Around("pointCut()")
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
