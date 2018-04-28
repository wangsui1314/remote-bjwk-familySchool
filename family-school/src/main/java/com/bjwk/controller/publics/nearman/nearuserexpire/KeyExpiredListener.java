package com.bjwk.controller.publics.nearman.nearuserexpire;

import com.bjwk.utils.RedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class KeyExpiredListener   extends JedisPubSub{
	@Override  
    public void onPSubscribe(String pattern, int subscribedChannels) {  
//        System.out.println("onPSubscribe "  
//                + pattern + " " + subscribedChannels);  
    }  
  
    @Override  
    public void onPMessage(String pattern, String channel, String message) {         
        Jedis jedis=RedisClient.getJedis();
    	jedis.select(1);
    	jedis.zrem("nearMan", message);
    	jedis.close();
    	System.out.println("附近的人...用户:"+message+"长时间未活动位置已失效....");
    }  
}
