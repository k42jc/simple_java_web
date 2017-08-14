package com.techeffic.smart4j.helper;

import com.techeffic.smart4j.constants.ConfigConstants;
import com.techeffic.smart4j.util.PropUtils;

/**
 * Created by liaoxudong on 2017/8/4.
 */
public class ConfigHelper {

    static{
        PropUtils.loadProps(ConfigConstants.CONFIG_FILE);
    }

    /**
     * 获取Jdbc驱动配置
     * @return
     */
    public static String getJDBCDriver(){
        return PropUtils.getProp(ConfigConstants.JDBC_DRIVER);
    }

    /**
     * 获取jdbc连接配置
     * @return
     */
    public static String getJDBCUrl(){
        return PropUtils.getProp(ConfigConstants.JDBC_URL);
    }

    /**
     * 获取数据库连接用户名
     * @return
     */
    public static String getJDBCUsername(){
        return PropUtils.getProp(ConfigConstants.JDBC_USERNAME);
    }


    /**
     * 获取数据库连接密码
     * @return
     */
    public static String getJDBCPassword(){
        return PropUtils.getProp(ConfigConstants.JDBC_PASSWORD);
    }

    /**
     * 获取基础包名
     * @return
     */
    public static String getBasePackage(){
        return PropUtils.getProp(ConfigConstants.BASE_PACKAGE);
    }

    /**
     * 获取页面模板文件存放目录 提供默认值
     * @return
     */
    public static String getViewPath(){
        return PropUtils.getProp(ConfigConstants.VIEW_PATH, ConfigConstants.DEFAULT_VIEW_PATH);
    }

    /**
     * 获取静态文件存放目录 提供默认值
     * @return
     */
    public static String getAssetPath(){
        return PropUtils.getProp(ConfigConstants.ASSET_PATH, ConfigConstants.DEFAULT_ASSET_PATH);
    }
}
