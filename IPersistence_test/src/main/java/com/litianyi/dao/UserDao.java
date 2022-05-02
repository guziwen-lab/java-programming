package com.litianyi.dao;

import com.litianyi.po.User;
import org.dom4j.DocumentException;

import java.util.List;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/3/9 8:13 PM
 */
public interface UserDao {

    //查询所有用户
    List<User> findAll();

    //根据条件查询
    User findByCondition(User user);

    Integer addUser(User user);
}
