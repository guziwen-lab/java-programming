package com.litianyi.io;

import java.io.InputStream;

/**
 * @author litianyi
 * @version 1.0
 * @date 2022/3/7 4:18 PM
 */
public class Resource {

    /**
     * 根据配置文件的路径，加载配置文件成字节输入流，存储在内存中
     * @param path 配置文件的路径
     * @return 字节输入流
     */
    public static InputStream getResourceAsStream(String path) {
       return Resource.class.getClassLoader().getResourceAsStream(path);
    }
}
