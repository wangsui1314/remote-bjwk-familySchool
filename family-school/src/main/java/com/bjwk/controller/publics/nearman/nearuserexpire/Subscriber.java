package com.bjwk.controller.publics.nearman.nearuserexpire;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.bjwk.utils.RedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 订阅类
 * @ClassName: Subscriber 
 * @Description: TODO("") 
 * @author: lihui 
 * @date: 2018年4月25日 下午11:04:27
 */
@Component
public class Subscriber  implements InitializingBean{

    @Override
	public  void afterPropertiesSet() {
		Jedis jedis= RedisClient.getJedis();
		jedis.psubscribe(new KeyExpiredListener(),"__keyevent@1__:expired");  
		jedis.close();
		System.out.println("监听key值失效启动....__keyevent@1__:expired");
	}  
	
}
