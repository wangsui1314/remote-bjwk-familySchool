package com.bjwk.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import com.alibaba.fastjson.JSONObject;


/** 
* @Description: http请求（GET|POST） 
* @author  Desolation
* @email:1071680460@qq.com
* @date 创建时间：2018年2月26日 下午8:30:07 
* @version 1.0  
*/
public class HttpUtility {
	
	private static final Log _logger = LogFactory.getLog(HttpUtility.class);

	/**
	 * 发送http get请求
	 * 
	 * @param getUrl
	 * @return
	 */
	public String sendGetRequest(String getUrl)
	{
		_logger.info("当前请求的URL为："+getUrl);
		
		StringBuffer sb = new StringBuffer();
		InputStreamReader isr = null;
		BufferedReader br = null;
		try
		{
			URL url = new URL(getUrl);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setAllowUserInteraction(false);
			
			isr = new InputStreamReader(url.openStream());
			br = new BufferedReader(isr);
			String line=null;
			while ((line = br.readLine()) != null)
			{
				sb.append(line);
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
		finally
		{
			try {
				if(isr!=null){
					isr.close();
				}
				if(br!=null){
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	/**
	 * JSON数据进行POST请求
	 * @param json1 [传递的json数据]
	 * @return string [请求后返回的结果]
	 */
	public String postByJson(String url,JSONObject json) {
		_logger.info("请求的接口为："+url);
		String jsonStr = json.toJSONString();

		@SuppressWarnings({ "resource", "deprecation" })
		HttpClient client = new DefaultHttpClient();

		HttpPost post = new HttpPost(url);

		post.setHeader("Content-type", "application/json;charset=utf-8");
		String result = "";

		try {

			StringEntity s = new StringEntity(jsonStr.toString(), "utf-8");
			s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=utf-8"));
			post.setEntity(s);
			// 发送请求
			HttpResponse httpResponse = client.execute(post);
			// 获取响应输入流
			InputStream inStream = httpResponse.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
			StringBuilder strber = new StringBuilder();
			String line = "";
			while ((line = reader.readLine()) != null)
				strber.append(line);
			inStream.close();
			result = strber.toString();
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				System.out.println("请求服务器成功，做相应处理");
			} else {
				System.out.println("请求服务端失败");
			}
			System.out.println(result);
		} catch (Exception e) {
			System.out.println("请求异常");
			e.getMessage();
			throw new RuntimeException(e);
		}
		return result;
	}
}
