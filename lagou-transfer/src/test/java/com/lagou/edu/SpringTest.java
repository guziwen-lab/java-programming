package com.lagou.edu;

import com.lagou.edu.dao.impl.JdbcAccountDaoImpl;
import com.lagou.edu.factory.BeanFactory;
import org.junit.Test;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/5/13 4:20 PM
 */
public class SpringTest {

    @Test
    public void test() {
        JdbcAccountDaoImpl accountDao = BeanFactory.getBean("accountDao");
        System.out.println(accountDao);
    }
}
