package com.litianyi.test;

import com.litianyi.dao.UserDao;
import com.litianyi.io.Resource;
import com.litianyi.po.User;
import com.litianyi.sql.session.SqlSession;
import com.litianyi.sql.session.SqlSessionFactory;
import com.litianyi.sql.session.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/3/7 4:25 PM
 */
public class IPersistenceTest {

    @Test
    public void testOne() throws DocumentException {
        InputStream in = Resource.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User(1L, "张三");
        User o = sqlSession.selectOne("com.litianyi.dao.UserDao.findByCondition", user);
        System.out.println(o);
    }

    @Test
    public void testList() throws DocumentException {
        InputStream in = Resource.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<User> users = sqlSession.selectList("com.litianyi.dao.UserDao.findAll");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testProxy() throws DocumentException {
        InputStream in = Resource.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.findByCondition(new User(1L, "张三"));
        System.out.println(user);
        List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }
    }
}
