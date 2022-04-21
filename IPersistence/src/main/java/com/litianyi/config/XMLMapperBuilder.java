package com.litianyi.config;

import com.litianyi.io.Resource;
import com.litianyi.pojo.Configuration;
import com.litianyi.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * 解析sql
 * @author litianyi
 * @version 1.0
 * @date 2022/3/7 8:34 PM
 */
public class XMLMapperBuilder {
    private final Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(Element rootElement) throws DocumentException {
        List<Element> mapperList = rootElement.selectNodes("//mapper");
        for (Element element : mapperList) {
            String mapperPath = element.attributeValue("resource");
            InputStream inputStream = Resource.getResourceAsStream(mapperPath);
            this.parse(inputStream);
        }
    }

    /**
     * 解析mapper.xml
     */
    public void parse(InputStream in) throws DocumentException {
        Document document = new SAXReader().read(in);
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");

        List<Element> list = rootElement.selectNodes("//select");
        list.forEach(element -> {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String parameterType = element.attributeValue("parameterType");
            String sql = element.getTextTrim();

            MappedStatement mappedStatement = new MappedStatement(id, resultType, parameterType, sql);
            String key = namespace + "." + id;
            configuration.getMappedStatementMap().put(key, mappedStatement);
        });
    }
}
