package com.litianyi.session;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/3/7 5:39 PM
 */
public interface SqlSessionFactory {

    SqlSession openSession();
}
