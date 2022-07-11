package com.lagou.edu.factory;

import com.lagou.edu.io.Resource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生产对象
 *
 * @author litianyi
 * @version 1.0
 * @date 2022/5/13 2:36 PM
 */
public class BeanFactory {

    private final static Map<String, Object> map = new HashMap<>();

    static {
        InputStream resource = Resource.getResourceAsStream("beans.xml");
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(resource);
            Element rootElement = document.getRootElement();

            List<Element> beanList = rootElement.selectNodes("//bean");
            for (Element element : beanList) {
                String id = element.attributeValue("id");
                String clazz = element.attributeValue("class");

                Object object = newInstance(clazz);
                map.put(id, object);
            }

            List<Element> properties = rootElement.selectNodes("//property");
            for (Element property : properties) {
                String name = property.attributeValue("name");
                String ref = property.attributeValue("ref");

                //父元素
                Element parent = property.getParent();
                String id = parent.attributeValue("id");
                Object parentBean = getBean(id);
                Method[] methods = parentBean.getClass().getDeclaredMethods();
                for (Method method : methods) {
                    if (("set" + name).equalsIgnoreCase(method.getName())) {
                        //给父元素的成员变量赋值
                        Object bean = getBean(ref);
                        method.invoke(parentBean, bean);
                        break;
                    }
                }
                map.put(id, parentBean);
            }

        } catch (DocumentException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object newInstance(String clazz) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<?> aClass = Class.forName(clazz);
        Constructor<?> constructor = aClass.getDeclaredConstructor();
        return constructor.newInstance();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String id) {
        return (T) map.get(id);
    }
}
