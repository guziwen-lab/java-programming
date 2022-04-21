package com.litianyi;

import com.litianyi.mapper.OrderMapper;
import com.litianyi.mapper.UserMapper;
import com.litianyi.po.Order;
import com.litianyi.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/3/13 9:53 PM
 */
public class JDBCTest {

    /**
     * 一对一
     */
    @Test
    public void listOrderWithUserTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = factory.openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Order> orders = mapper.listOrderWithUser();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    /**
     * 一对多
     */
    @Test
    public void listUserWithOrderTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.listUserWithOrder();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 多对多
     */
    @Test
    public void listUserWithRoleTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.listUserWithOrderRole();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
