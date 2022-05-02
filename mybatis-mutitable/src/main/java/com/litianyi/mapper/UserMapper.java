package com.litianyi.mapper;

import com.litianyi.po.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.mybatis.caches.redis.RedisCache;

import java.util.List;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/3/13 9:40 PM
 */
@CacheNamespace(implementation = RedisCache.class) // 开启二级缓存
public interface UserMapper {

    List<User> listUserWithOrder();

    List<User> listUserWithRole();

    List<User> listUserWithOrderRole();

    @Insert("insert into t_user values (#{id},#{username},#{password},#{birthday})")
    void addUser(User user);

    @Update("update t_user set username = #{username} where id = #{id} ")
    void updateUser(User user);

    @Select("select * from t_user")
    List<User> selectUsers();

    @Delete("delete from t_user where id=#{id}")
    void deleteUserById(Long id);

    @Select("select * from t_user where id = #{id} ")
    User getById(Long id);
}
