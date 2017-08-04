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

    public static String getJDBCUsername(){
        return PropUtils.getProp(ConfigConstants.JDBC_USERNAME);
    }
}
