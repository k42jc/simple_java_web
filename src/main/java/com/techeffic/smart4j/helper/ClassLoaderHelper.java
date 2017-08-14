package com.techeffic.smart4j.helper;

import com.techeffic.smart4j.annotation.Controller;
import com.techeffic.smart4j.annotation.Service;
import com.techeffic.smart4j.loader.SmartClassLoader;

import java.util.HashSet;
import java.util.Set;

/**
 * 类加载帮助类
 * Created by liaoxudong on 2017/8/4.
 */
public class ClassLoaderHelper {
    private static Set<Class<?>> classSet = new HashSet<Class<?>>();

    static{
        String basePackage = ConfigHelper.getBasePackage();
        classSet.addAll(SmartClassLoader.getClassSet(basePackage));
    }

    /**
     * 获取所有已加载的类集合
     * @return
     */
    public static Set<Class<?>> getClassSet(){
        return classSet;
    }

    /**
     * 获取所有Controller集合
     * @return
     */
    public static Set<Class<?>> getControllerSet(){
        Set<Class<?>> controllerSet = new HashSet<Class<?>>();
        for(Class<?> clazz:classSet){
            if (clazz.isAnnotationPresent(Controller.class)) {
                controllerSet.add(clazz);
            }
        }
        return controllerSet;
    }

    /**
     * 获取所有Service
     * @return
     */
    public static Set<Class<?>> getServiceSet() {
        Set<Class<?>> serviceSet = new HashSet<Class<?>>();
        for(Class<?> clazz:classSet){
            if (clazz.isAnnotationPresent(Service.class)) {
                serviceSet.add(clazz);
            }
        }
        return serviceSet;
    }

    /**
     * 获取项目中所有的bean 即定义为@Controller 与 @Service 注解的类
     * @return
     */
    public static Set<Class<?>> getAllBeans(){
        Set<Class<?>> beanSet = new HashSet<Class<?>>();
        for(Class<?> clazz:classSet){
            if (clazz.isAnnotationPresent(Service.class) || clazz.isAnnotationPresent(Service.class)) {
                beanSet.add(clazz);
            }
        }
        return beanSet;
    }
}
