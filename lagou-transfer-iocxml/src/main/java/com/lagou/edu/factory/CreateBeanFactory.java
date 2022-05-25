package com.lagou.edu.factory;

import com.lagou.edu.utils.ConnectionUtils;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/5/19 5:17 PM
 */
public class CreateBeanFactory {

    public ConnectionUtils getInstant() {
        return ConnectionUtils.getInstance();
    }
}
