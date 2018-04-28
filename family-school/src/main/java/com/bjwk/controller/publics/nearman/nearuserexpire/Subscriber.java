package com.bjwk.controller.publics.nearman.nearuserexpire;

import org.springframework.stereotype.Component;

import com.bjwk.utils.RedisClient;

import redis.clients.jedis.Jedis;

/**
 * 订阅类
 * @ClassName: Subscriber 
 * @Description: TODO("") 
 * @author: lihui 
 * @date: 2018年4月25日 下午11:04:27
 */

public class Subscriber extends Thread{

//	@Autowired 
//	private Xxxxx  xxxx;
	
	public  void afterPropertiesSet() {
		/**
		 * 这里可以加入别的逻辑策略
		 */
		initTwo();
	}  
    
    @Override
	public void run(){
    	System.out.println("监听key值失效启动....__keyevent@1__:expired");
		Jedis jedis= RedisClient.getJedis();
		/**
		 * psubscribe阻塞方法 所以需要启线程
		 */
		jedis.psubscribe(new KeyExpiredListener(),"__keyevent@1__:expired");  
		
	}
    
    private void initTwo(){
		// TODO Auto-generated method stub
		Thread thread=new  Thread(new Subscriber());
		thread.start();
	}
	
}
