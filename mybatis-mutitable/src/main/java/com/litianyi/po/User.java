package com.litianyi.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/3/7 12:02 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private Date birthday;

    private List<Order> orders = new ArrayList<>();
    private List<Role> roles = new ArrayList<>();
}
