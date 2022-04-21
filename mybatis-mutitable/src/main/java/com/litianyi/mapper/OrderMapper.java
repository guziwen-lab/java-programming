package com.litianyi.mapper;

import com.litianyi.po.Order;

import java.util.List;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/3/14 10:53 AM
 */
public interface OrderMapper {
    List<Order> listOrderWithUser();
}
