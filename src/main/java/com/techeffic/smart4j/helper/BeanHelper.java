package com.techeffic.smart4j.helper;

import com.techeffic.smart4j.util.ReflectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Bean帮助类
 * Created by liaoxudong on 2017/8/4.
 */
public class BeanHelper {
    /** 定义用于存放Bean类与Bean示例之间的映射*/
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    static{
        Set<Class<?>> allBeans = ClassLoaderHelper.getAllBeans();
        for (Class<?> bean : allBeans) {
            BEAN_MAP.put(bean, ReflectionUtils.newInstance(bean));
        }
    }

    /**
     * 获取Bean映射
     * @return
     */
    public static Map<Class<?>, Object> getBeanMap(){
        return BEAN_MAP;
    }

    /**
     * 获取bean实例
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        if (!BEAN_MAP.containsKey(clazz)) {
            throw new RuntimeException("Can't get bean by class:"+clazz);
        }
        return (T)BEAN_MAP.get(clazz);
    }


}
