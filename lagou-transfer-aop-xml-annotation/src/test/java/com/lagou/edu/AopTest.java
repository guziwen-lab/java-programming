package com.lagou.edu;

import com.lagou.edu.pojo.Result;
import com.lagou.edu.service.TransferService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/5/13 4:20 PM
 */
public class AopTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        Result result = (Result) applicationContext.getBean("lazyResult");
        System.out.println(result);

        applicationContext.close();
    }

    @Test
    public void testAop() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        TransferService transferService = applicationContext.getBean(TransferService.class);
        transferService.transfer("6029621011001", "6029621011000", 50);

        applicationContext.close();
    }
}
