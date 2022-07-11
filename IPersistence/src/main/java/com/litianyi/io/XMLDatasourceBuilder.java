package com.litianyi.io;

import com.litianyi.config.Configuration;
import com.zaxxer.hikari.HikariDataSource;
import org.dom4j.Element;

import java.util.List;
import java.util.Properties;

/**
 * 解析 datasource
 * @author litianyi
 * @version 1.0
 * @date 2022/3/8 10:13 AM
 */
public class XMLDatasourceBuilder {
    private final Configuration configuration;

    public XMLDatasourceBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(Element rootElement) {
        List<Element> datasourcePropertyList = rootElement.selectNodes("//property");

        Properties properties = new Properties();
        datasourcePropertyList.forEach(element -> {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name, value);
        });

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(properties.getProperty("driverClass"));
        dataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));
        configuration.setDataSource(dataSource);
    }
}
