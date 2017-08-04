package com.techeffic.smart4j.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * Created by liaoxudong on 2017/8/4.
 */
public class DateUtils {

    private static final String DATE_TIME_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式化日期 如果入参为空则返回当前时间的格式化
     * @param date
     * @return
     */
    public static String getFormatDatetime1(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT_1);
        if(date == null)
            return dateFormat.format(new Date());
        return dateFormat.format(date);
    }

    /**
     * 获取当前时间的格式化字符串
     * @return
     */
    public static String getNowDateformat1(){
        return getFormatDatetime1(null);
    }
}
