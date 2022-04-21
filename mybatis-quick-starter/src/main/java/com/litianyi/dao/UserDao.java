package com.litianyi.dao;

import com.litianyi.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/3/11 3:47 PM
 */
public interface UserDao {
    List<User> findAll();

    void saveUser(User user);

    List<User> findByCondition(User user);

    List<User> findByIds(long[] ids);
}
