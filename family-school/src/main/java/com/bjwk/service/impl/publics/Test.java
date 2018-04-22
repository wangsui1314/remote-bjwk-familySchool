package com.bjwk.service.impl.publics;

import redis.clients.jedis.Jedis;

public class Test {
  public static void main(String[] args) {
	Jedis jedis=new Jedis("www.wangsui.top",6379);
	System.out.println(jedis);
}
}
