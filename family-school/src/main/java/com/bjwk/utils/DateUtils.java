package com.bjwk.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
 * @Description: 时间的工具类
 * @author  Desolation
 * @email:1071680460@qq.com
 * @date 创建时间：2017年7月31日 上午9:59:57 
 * @version 1.0 
 */
public class DateUtils {

	private static final Log _logger = LogFactory.getLog(DateUtils.class);

	
	/**
	 * 获取当前时间的字符串 yyMMddhhmmss
	 * 格式：年月日时分秒 两位
	 * @return
	 */
	public static String getTime(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
		String s = sdf.format(c.getTime());
		return  s;
	}
	/**
	 * 获取当前时间的字符串
	 * 格式：年月日时分秒 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getTimeFour(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = sdf.format(c.getTime());
		return  s;
	}
	
	/**
	 * 获取当前时间的字符串 年份为四位数
	 * 格式：年月日时分秒 yyyyMMddHHmmss
	 * @return
	 */
	public static String getTimeStrForFour(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = sdf.format(c.getTime());
		return  s;
	}
	/**
	 * 将yyyyMMddHHmmss格式转成 yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String getDateTime(String date){
		String reg = "(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})";
		return date.replaceAll(reg, "$1-$2-$3 $4:$5:$6");
	}
	/**
	 * 获取两个时间的时间差
	 * getDifferTime("2017-01-01 11:12:12", "2017-01-01 11:13:12")
	 */
	public static double getDifferTime(String beginTime,String endTime){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date beginDate = sdf.parse(beginTime);
			Date endDate = sdf.parse(endTime);
			Long time1 = endDate.getTime() - beginDate.getTime();
			time1=time1/1000;
			return time1;
		} catch (ParseException e) {
			_logger.error("获取时间错误",e);
			_logger.info(e+"获取时间差错误");
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 获取当前时间的字符串
	 * @param formatDate 时间格式
	 * @return
	 */
	public static String getTime(String formatDate){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
		String s = sdf.format(c.getTime());
		return  s;
	}
}
