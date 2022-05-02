package com.litianyi.mapper;

import com.litianyi.po.Order;
import com.litianyi.po.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/3/14 10:53 AM
 */
public interface OrderMapper {
    List<Order> listOrderWithUser();

    @Select("select * from t_order where id = #{id} ")
    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderTime", column = "order_time"),
            @Result(property = "total", column = "total"),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "com.litianyi.mapper.UserMapper.getById"))
    })
    Order getOrderWithUser(@Param("id") Long id);
}
