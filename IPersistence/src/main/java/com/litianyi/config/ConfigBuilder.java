package com.litianyi.config;

import com.litianyi.pojo.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * 将配置文件进行解析，封装为 Configuration
 *
 * @author litianyi
 * @version 1.0
 * @date 2022/3/7 5:40 PM
 */
public class ConfigBuilder {

    private final Configuration configuration;

    public ConfigBuilder() {
        this.configuration = new Configuration();
    }

    /**
     * 将配置文件进行解析，封装为 Configuration
     */
    public Configuration parseConfig(InputStream in) throws DocumentException {
        Document document = new SAXReader().read(in);
        Element rootElement = document.getRootElement();

        // 解析 SqlMapConfig.xml
        XMLDatasourceBuilder xmlDatasourceBuilder = new XMLDatasourceBuilder(configuration);
        xmlDatasourceBuilder.parse(rootElement);

        // 解析 mapper.xml：拿到路径-->字节输入流-->dom4j解析
        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
        xmlMapperBuilder.parse(rootElement);

        return configuration;
    }
}
