package com.techeffic.smart4j.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类 用于类的初始化
 * Created by liaoxudong on 2017/8/4.
 */
public class ReflectionUtils {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);

    /**
     * 创建实例
     * @param clazz
     * @return
     */
    public static Object newInstance(Class<?> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            logger.error("实例化【{}】失败！",clazz,e);
        }
        return null;
    }

    /**
     * 执行方法
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static Object invokeMethod(Object obj, Method method, Object... args) {
        try {
            method.setAccessible(true);
            return method.invoke(obj, args);
        } catch (Exception e) {
            logger.error("反射调用【{}】方法失败!",method,e);
        }
        return null;
    }

    /**
     * 实例变量设置值
     * @param obj
     * @param field
     * @param value
     */
    public static void setField(Object obj, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            logger.error("设置【{}】字段失败！",field,e);
        }
    }
}
