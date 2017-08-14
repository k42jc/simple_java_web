package com.techeffic.smart4j.helper;

import com.techeffic.smart4j.annotation.Action;
import com.techeffic.smart4j.po.Handler;
import com.techeffic.smart4j.po.Request;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 控制器帮助类
 * Created by liaoxudong on 2017/8/5.
 */
public final class ControllerHelper {

    /**存放Request与Handler映射*/
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static{
        // 获取所有Controller
        Set<Class<?>> controllerSet = ClassLoaderHelper.getControllerSet();
        for (Class<?> controller : controllerSet) {
            // 获取Controller中所有定义的方法
            Method[] methods = controller.getDeclaredMethods();
            for (Method method : methods) {
                // 如果当前方法是@Action注解修饰
                if (method.isAnnotationPresent(Action.class)) {
                    //从Action注解中获取URL规则
                    Action action = method.getAnnotation(Action.class);
                    String mapping = action.value();
                    // 是否匹配【一个或多个字母或数字开头，中间:冒号分割，接着是/斜杠，然后以0个或多个字母或数字结尾】
                    if (mapping.matches("\\w+:/\\w*")) {
                        String[] array = mapping.split(":");
                        if (array.length == 2) {
                            // 获取请求方法与请求路径
                            String requestMethod = array[0];
                            String requestPath = array[1];
                            Request request = new Request(requestMethod, requestPath);
                            Handler handler = new Handler(controller, method);
                            // 放入ACTION_MAP映射
                            ACTION_MAP.put(request, handler);
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取Handler
     * @param requestMethod
     *
     * @param requestPath
     * @return
     */
    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}
