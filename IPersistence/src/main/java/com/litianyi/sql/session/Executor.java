package com.litianyi.sql.session;

import com.litianyi.pojo.MappedStatement;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/3/8 3:56 PM
 */
public interface Executor {

    <E> List<E> query(DataSource dataSource, MappedStatement statement, Object... params) throws Exception;
}
