package com.techeffic.smart4j.util;

/**
 * 字符串工具类
 * Created by liaoxudong on 2017/8/4.
 */
public class StringUtils {

    public static boolean isEmpty(String str){
        return str == null || "".equals(str.trim());
    }

    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }


    private StringUtils(){}
}
