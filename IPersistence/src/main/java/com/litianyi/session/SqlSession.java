package com.litianyi.session;

import java.util.List;

/**
 * 查询参数如果是多个，要求每个参数都是基本类型或者基本类型的包装类型或者 String 类型，必须使用 {@link com.litianyi.annotation.Param} 注解参数。
 * 如果参数是复杂对象，要在 mapper.xml 中注明参数类型，不要使用 {@link com.litianyi.annotation.Param} 注解参数，注解了也没用。
 * 返回结果必须注明对象类型。
 *
 * @author litianyi
 * @version 1.0
 * @date 2022/3/8 10:42 AM
 */
public interface SqlSession {

    /**
     * 查询所有
     */
    <E> List<E> selectList(String statementId, Object... params);

    /**
     * 查询单个
     */
    <T> T selectOne(String statementId, Object... params);

    Integer insert(String statement, Object... params);

    Integer update(String statement, Object... params);

    Integer delete(String statement, Object... params);

    //为dao接口生成代理实现类
    <T> T getMapper(Class<? extends T> mapperClass);
}
