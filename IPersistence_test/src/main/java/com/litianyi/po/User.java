package com.litianyi.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/3/7 12:02 PM
 */
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private Date birthday;
}
