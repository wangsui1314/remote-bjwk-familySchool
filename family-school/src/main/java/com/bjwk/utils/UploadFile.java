package com.bjwk.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;


public class UploadFile {
	public static final String accessKey = "Kc3LeDlJPvS0TycgG3WzKwnNgR0P_CMtIT2_qrtV";
	public static final String secretKey = "DWusXUefOAV7zH81c9fFWqmVN0obpXxSKhc9ngDh";
	public static final String bucket = "bojian";
//	public static final String domainOfBucket = "https://portal.qiniu.com/bucket/yayi";
	public static String getUpToken(){
		Auth auth = Auth.create(accessKey, secretKey);
		StringMap putPolicy = new StringMap();
		long expireSeconds = 3600;
		String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
		System.out.println(upToken);
		return upToken;
	}
//	public static String getUrl(String fileName) throws UnsupportedEncodingException{
//		String encodedFileName = URLEncoder.encode(fileName, "utf-8");
//		String finalUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
//		return finalUrl;
//		
//	}
}


