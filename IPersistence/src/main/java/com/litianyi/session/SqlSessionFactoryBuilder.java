package com.litianyi.session;

import com.litianyi.config.ConfigBuilder;
import com.litianyi.config.Configuration;
import org.dom4j.DocumentException;

import java.io.InputStream;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/3/7 5:04 PM
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream in) {
        //解析配置文件，将解析出来的内容封装到容器对象 Configuration 中
        ConfigBuilder configBuilder = new ConfigBuilder();
        Configuration configuration;
        try {
            configuration = configBuilder.parseConfig(in);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

        //创建SqlSessionFactory对象：工厂类：生产 SqlSession 会话对象
        return new DefaultSqlSessionFactory(configuration);
    }
}
