package com.bjwk.service.parent;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class Test {
    public static void main(String[] args) {
    	 ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext-redis.xml");
         final RedisTemplate<String, Object> redisTemplate = appCtx.getBean("redisTemplate",RedisTemplate.class);
         ValueOperations<String, Object> value = redisTemplate.opsForValue();
         value.set("lp", "hello word");
         //获取 这个 key 的值
         System.out.println(value.get("lp"));
	}
}
