package com.litianyi.session;

import com.litianyi.config.Configuration;
import com.litianyi.mapping.MappedStatement;

import java.util.List;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/3/8 3:56 PM
 */
public interface Executor {

    <E> List<E> query(Configuration configuration, MappedStatement statement, Object[] params) throws Exception;

    Integer update(Configuration configuration, MappedStatement mappedStatement, Object[] params) throws Exception;

    Integer delete(Configuration configuration, MappedStatement mappedStatement, Object[] params) throws Exception;
}
