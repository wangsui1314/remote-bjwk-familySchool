package com.bjwk.controller.publics;

import redis.clients.jedis.Jedis;

public class RedisTest {
   public static void main(String[] args) {
	Jedis jedis =new Jedis("140.143.61.179",6379);
	System.out.println(jedis.set("1","2"));
}
}
