package com.techeffic.smart4j.po;

import java.lang.reflect.Method;

/**
 * 封装Action信息
 * Created by liaoxudong on 2017/8/4.
 */
public class Handler {
    /**Controller类*/
    private Class<?> controllerClass;

    /**Action方法*/
    private Method actionMethod;


    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
