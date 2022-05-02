package com.litianyi;

import com.litianyi.mapper.OrderMapper;
import com.litianyi.mapper.UserMapper;
import com.litianyi.po.Order;
import com.litianyi.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/3/13 9:53 PM
 */
public class JDBCTest {

    private SqlSession sqlSession;

    private UserMapper userMapper;

    private OrderMapper orderMapper;

    @Before
    public void beforeExecute() throws IOException {
        InputStream resource = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        this.sqlSession = build.openSession(true);
        this.userMapper = sqlSession.getMapper(UserMapper.class);
        this.orderMapper = sqlSession.getMapper(OrderMapper.class);
    }

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

    @Test
    public void addUserTest() {
        userMapper.addUser(new User(null, "chong", "1234", new Date(), null, null));
    }

    @Test
    public void getOrderWithUserTest() {
        Order orderWithUser = orderMapper.getOrderWithUser(1L);
        System.out.println(orderWithUser);
    }
}
