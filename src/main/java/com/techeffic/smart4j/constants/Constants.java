package com.techeffic.smart4j.constants;

/**
 * 系统所需常量
 * Created by liaoxudong on 2017/8/4.
 */
public class Constants {

    /** 当前系统文件分隔符 */
    public static final String SEPARATOR = System.getProperty("file.separator");

    /** 点*/
    public static final String POINT = ".";

    /**通过Thread.currentThread().getContextClassLoader().getResource("").getPath()获取到的路径空格会被%20替换*/
    public static final String URL_BLANK_SHOW = "%20";
    /**空格*/
    public static final String BLANK = " ";
    /**class文件后缀*/
    public static final String CLASS_SUFFIX = ".class";
    /**普通文件类型*/
    public static final String FILE = "file";
    /**jar包类型*/
    public static final String JAR = "jar";
}
