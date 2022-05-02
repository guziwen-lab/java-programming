package com.litianyi;

import com.litianyi.mapper.OrderMapper;
import com.litianyi.mapper.UserMapper;
import com.litianyi.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/4/23 6:32 PM
 */
public class CacheTest {

    private SqlSessionFactory sqlSessionFactory;

    private SqlSession sqlSession;

    private UserMapper userMapper;

    private OrderMapper orderMapper;

    @Before
    public void beforeExecute() throws IOException {
        InputStream resource = Resources.getResourceAsStream("SqlMapConfig.xml");
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        this.sqlSession = this.sqlSessionFactory.openSession();
        this.userMapper = this.sqlSession.getMapper(UserMapper.class);
        this.orderMapper = this.sqlSession.getMapper(OrderMapper.class);
    }

    @Test
    public void firstLevelCacheTest() {
        User user1 = userMapper.getById(6L);

//        User user = new User();
//        user.setId(6L);
//        user.setUsername("tom");
//        userMapper.updateUser(user);
//        sqlSession.commit();

        User user2 = userMapper.getById(6L);

        User user3 = userMapper.getById(6L);

        User user4 = userMapper.getById(6L);

        System.out.println(user1 == user2);
        System.out.println(user1 == user3);
        System.out.println(user1 == user4);
    }

    @Test
    public void secondLevelCacheTest() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();

        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);

        User user1 = userMapper1.getById(6L);
        sqlSession1.close();
        User user2 = userMapper2.getById(6L);
        sqlSession2.close();

        System.out.println("\nuser1==user2?" + (user1 == user2) + "\n");

        User user = new User();
        user.setId(6L);
        user.setUsername("tom");
        userMapper3.updateUser(user);
        sqlSession3.commit();

//        User user22 = userMapper2.getById(6L);
    }
}
