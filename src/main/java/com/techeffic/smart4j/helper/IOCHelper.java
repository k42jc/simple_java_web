package com.techeffic.smart4j.helper;

import com.techeffic.smart4j.annotation.Inject;
import com.techeffic.smart4j.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * IOC帮助类 用于类加载过程中对具有@Inject注解的字段进行注入
 * Created by liaoxudong on 2017/8/4.
 */
public class IOCHelper {

    static {
        // 获取所有bean映射
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
            Class<?> clazz = beanEntry.getKey();
            Object obj = beanEntry.getValue();
            // 获取当前bean的所有字段，包括Public protected private但不包含父类
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                // 当前字段是@Inject修饰
                if (field.isAnnotationPresent(Inject.class)) {
                    // 获取当前注解所对应字段的类型
                    Class<?> fieldType = field.getType();
                    // 获取对应的实例化bean值
                    Object value = beanMap.get(fieldType);
                    // 通过反射注入字段
                    ReflectionUtils.setField(obj, field, value);
                }
            }

        }
    }
}
