package com.bjwk.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tian on 16/10/18.
 */
public class TimeUtil {
    public static String changeDateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String timeString = sdf.format(date);
        return timeString;
    }
    public static Date changeStringToDate(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return sdf.parse(time);
    }
    public static Date changeStringToDateTwo(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(time);
    }
    public static Long timeBetween(Date oldDate, Date nowDate){
        return Math.abs(nowDate.getTime()-oldDate.getTime());
    }
}
