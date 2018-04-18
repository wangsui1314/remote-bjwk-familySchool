package com.bjwk.controller.parent;

import redis.clients.jedis.Jedis;

public class Test {

	public static void main(String[] args) {
		Jedis jedis=null;
		jedis = new Jedis("www.wangsui.top",6379);
		jedis.set("lihui", "123");
		System.out.println(jedis.get("lihui"));
//		try {
//			
//		} catch (Exception e) {
//			if(jedis != null){
//				jedis.close();
//			}
//
//		}

	}

}
