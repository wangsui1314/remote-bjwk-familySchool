package com.bjwk.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Description: 读取配置文件信息
 * @author Desolation
 * @email:1071680460@qq.com
 * @date 创建时间：2017年12月26日 下午12:09:19
 * @version 1.0
 */
public class ReadProperties {
	
	private static final Log _logger = LogFactory.getLog(ReadProperties.class);
	
	/**
	 * 
	 * @return
	 */
	public static String getUrl(String filePath, String param) {
		_logger.info("读取配置文件信息：文件名："+filePath+",读取的数据为："+param);
		InputStream in = null;
		Properties properties = new Properties();
		try {
			// 使用ClassLoader加载properties配置文件生成对应的输入流
			in = ReadProperties.class.getClassLoader().getResourceAsStream(filePath);
			properties.load(in);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return properties.getProperty(param);
	}

}
