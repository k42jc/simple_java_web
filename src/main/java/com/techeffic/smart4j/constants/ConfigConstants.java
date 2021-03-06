package com.techeffic.smart4j.constants;

/**
 * 存放配置相关常量
 * Created by liaoxudong on 2017/8/4.
 */
public class ConfigConstants {

    /**指定配置文件名*/
    public static final String CONFIG_FILE = "config.properties";

    /**默认配置常量*/
    public static final String JDBC_DRIVER = "smart4j.jdbc.driver";
    public static final String JDBC_URL = "smart4j.jdbc.url";
    public static final String JDBC_USERNAME = "smart4j.jdbc.username";
    public static final String JDBC_PASSWORD = "smart4j.jdbc.password";
    public static final String BASE_PACKAGE = "smart4j.app.base_package";

    /**可选配置*/
    public static final String VIEW_PATH = "smart4j.app.view_path";
    public static final String ASSET_PATH = "smart4j.app.asset_path";

    public static final String DEFAULT_VIEW_PATH = "WEB-INF" +Constants.SEPARATOR +"view" + Constants.SEPARATOR;

    public static final String DEFAULT_ASSET_PATH = "WEB-INF" + Constants.SEPARATOR +"asset" +Constants.SEPARATOR;


    public static void main(String[] args) {
        System.out.println(DEFAULT_ASSET_PATH);
    }





}
