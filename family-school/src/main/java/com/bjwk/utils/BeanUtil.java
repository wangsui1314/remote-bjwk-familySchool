package com.bjwk.utils;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanUtil implements ApplicationContextAware {

	 private static ApplicationContext context = null;

	    /* (non lh)
	     * @Title: setApplicationContext
	     * @Description: spring获取bean工具类
	     * @param applicationContext
	     * @throws BeansException
	     */
	    @Override
	    public void setApplicationContext(ApplicationContext applicationContext)
	            throws BeansException {
	        context = applicationContext;
	    }

	    public static <T> T getBean(String beanName){
	        return (T) context.getBean(beanName);
	    }

	    public static String getMessage(String key){
	        return context.getMessage(key, null, Locale.getDefault());
	    }
   
}