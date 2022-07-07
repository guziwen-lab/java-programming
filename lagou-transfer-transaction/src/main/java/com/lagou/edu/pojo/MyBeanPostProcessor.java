package com.lagou.edu.pojo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 拦截实例化之后的对象（实例化并且属性注入了）
 *
 * @author litianyi
 * @version 1.0
 * @date 2022/5/30 10:55 AM
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("lazyResult".equalsIgnoreCase(beanName)) {
            System.out.println("MyBeanPostProcessor before 方法拦截处理lazyResult");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("lazyResult".equalsIgnoreCase(beanName)) {
            System.out.println("MyBeanPostProcessor after 方法拦截处理lazyResult");
        }
        return bean;
    }
}
