package com.bjwk.utils;

import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.Jedis;
/**
 * 
 * @ClassName: UserNameUtil 
 * @Description: TODO("获取用户用户名") 
 * @author: lihui 
 * @date: 2018年3月30日 下午11:14:15
 */
public class UserNameUtil {
    
   public static String getUserNameBytoken(String token) {
	  Jedis jedis=RedisClient.getJedis();
	 String userName=jedis.hget("loginStatus",token);
	 jedis.close();
	   return   userName ;
   }
}
