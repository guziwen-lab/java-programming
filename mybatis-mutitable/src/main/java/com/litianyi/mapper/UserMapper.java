package com.litianyi.mapper;

import com.litianyi.po.Order;
import com.litianyi.po.User;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/3/13 9:40 PM
 */
public interface UserMapper {

    List<User> listUserWithOrder();

    List<User> listUserWithRole();

    List<User> listUserWithOrderRole();

    @Insert("insert into t_user values (#{id},#{username},#{password},#{birthday})")
    void addUser(User user);
}
