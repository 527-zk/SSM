package com.ssm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * 日期格式的工具类
 * @author kneesh
 * @date 2021/4/20-17:58
 */
public class DateUtils {
    /**
     * 将日期格式转化为自定义类型的字符串格式
     * @param date
     * @param format
     * @return String
     */
    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf;
        if (format != null && !Objects.equals(format, "")) {
            sdf = new SimpleDateFormat(format);
        } else {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        return sdf.format(date);
    }

    /**
     * 将字符串转化为相应的日期格式
     * @param str
     * @param format
     * @return Date
     * @throws ParseException
     */
    public static Date stringToDate(String str,String format) throws ParseException {
        SimpleDateFormat sdf;
        if (format != null && !Objects.equals(format, "")) {
            sdf = new SimpleDateFormat(format);
        } else {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        return sdf.parse(str);
    }

}
