package com.techeffic.smart4j.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * 属性文件加载工具类
 * Created by liaoxudong on 2017/8/4.
 */
public class PropUtils {

    private static final Logger logger = LoggerFactory.getLogger(PropUtils.class);

//    private static final String RESOURCE_DIR = "/src/main/resources/";

    private static Properties prop = new Properties();

    /**
     * 加载属性配置文件
     *
     * @param propFileName 单个配置文件名
     */
    public static void loadProps(String propFileName) {
        try {
            URL resource = Thread.currentThread().getContextClassLoader().getResource("");
            logger.debug("加载配置文件位置：{}", resource);
            FileInputStream inputStream = new FileInputStream(new File(resource.getPath() + propFileName));
            prop.load(inputStream);
        } catch (FileNotFoundException e) {
            logger.error("找到不到配置文件...", e);
        } catch (IOException e) {
            logger.error("读取文件失败...", e);
        }
    }

    /**
     * 根据属性配置key获取value
     *
     * @param key          属性key
     * @param defaultValue 默认值
     * @param clazz        转换类型
     * @return
     */
    public static <T> T getProp(String key, String defaultValue, Class<T> clazz) {
        if (prop.isEmpty()) {
            logger.warn("暂未加载配置！");
            return null;
        }
        String value = prop.getProperty(key, defaultValue);
        if ("Integer".equals(clazz.getSimpleName())) {
             value = StringUtils.isNotEmpty(value)?value:"0";
            return (T) Integer.valueOf(value);
        }
        if ("Double".equals(clazz.getSimpleName())) {
            value = StringUtils.isNotEmpty(value)?value:"0.0";
            return (T) Double.valueOf(value);
        }

        if ("Long".equals(clazz.getSimpleName())) {
            value = StringUtils.isNotEmpty(value)?value:"0";
            return (T) Long.valueOf(value);
        }
        return (T) value;
    }


    public static String getProp(String key, String defaultValue) {
        return getProp(key, defaultValue, String.class);
    }

    public static String getProp(String key) {
        return getProp(key, "");
    }

    public static <T> T getProp(String key, Class<T> clazz) {
        return getProp(key, "", clazz);
    }

    public static void main(String[] args) {
        loadProps("config.properties");

        String prop = getProp("smart4j.jdbc.driver");
        System.out.println(prop);

        Integer test = getProp("test", Integer.class);
        System.out.println(test);


        Double test2 = getProp("test3", Double.class);
        System.out.println(test2);
    }


    private PropUtils() {
    }
}
