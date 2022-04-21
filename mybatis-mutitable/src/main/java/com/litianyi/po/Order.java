package com.litianyi.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/3/12 9:08 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private Date orderTime;
    private Integer total;

    private User user;
}
