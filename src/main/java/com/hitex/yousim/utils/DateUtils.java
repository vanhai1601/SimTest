package com.hitex.yousim.utils;

import com.hitex.yousim.constant.Constant;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    private static String formatter = "yyyyMMddHHmmss";
    public static final String PATTERN_YMD_HIS = "yyyy-MM-dd HH:mm:ss";

    public static Date setExpireDate (Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, 5);
        return c.getTime();
    }

    public static LocalDateTime convertStringToLocalDateTime(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = null;
        if (StringUtils.isNotEmpty(date)) {
             dateTime = LocalDateTime.parse(date, formatter);
        }
        return dateTime;
    }
    public static String convertLocalDateTimeToString(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = date.format(formatter);
        return formatDateTime;
    }
    public static String convertLocalDateTimeToString(LocalDateTime date, String pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String formatDateTime = date.format(formatter);
        return formatDateTime;
    }
    public static java.sql.Date convertStringToDate(String dateString) throws ParseException {
       Date dates = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        java.sql.Date date = new java.sql.Date(dates.getTime());
        return date;
    }

    public static java.sql.Date convertStringToDate(String dateString, String formatter) throws ParseException {
        Date dates = new SimpleDateFormat(formatter).parse(dateString);
        java.sql.Date date = new java.sql.Date(dates.getTime());
        return date;
    }

    public static String convertDateToString(Date date){
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String formatDateTime = format.format(date);
        return formatDateTime;
    }

    public static String parseDateToString(LocalDateTime localDateTime, String formatter) {
        return DateTimeFormatter.ofPattern(formatter).format(localDateTime);
    }
    public static String parseDateToStringDefault(LocalDateTime time) {
        return parseDateToString(time, Constant.FORMAT_DATE_TIME);
    }
    public static String getYmdHis() {
        return getTimeNowFormat(PATTERN_YMD_HIS);
    }
    public static String getTimeNowFormat(String format) {
        return new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
    }

}
