package com.lagou.edu;

import com.lagou.edu.service.TransferService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/5/13 4:20 PM
 */
public class IoCTest {

    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        TransferService transferService = (TransferService) applicationContext.getBean("transferService");
        System.out.println(transferService);
    }
}
