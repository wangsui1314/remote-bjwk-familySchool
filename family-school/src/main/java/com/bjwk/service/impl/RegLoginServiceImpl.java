package com.bjwk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjwk.dao.RegLoginDao;
import com.bjwk.model.pojo.Users;
import com.bjwk.service.RegLoginService;
import com.bjwk.utils.CallStatusEnum;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.RedisClient;

import redis.clients.jedis.Jedis;


@Service
public class RegLoginServiceImpl  implements RegLoginService{
	@Autowired
	private RegLoginDao regLoginDao;

	
	public DataWrapper<Users> reg(Users user) {
		System.out.println(123);
		DataWrapper<Users> dataWrapper=new DataWrapper<Users>();
		/** 
		 * 判断账号是否存在
		 * insert some code
		 */
		if(regLoginDao.queryUserIsTrue(user.getUserName())!=null){
			dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			dataWrapper.setMsg("用户名已存在");
			return dataWrapper;
		}

		if(regLoginDao.reg(user)!=0){
			Jedis jedis=null;
			String token=null;
			
				jedis=RedisClient.getInstance().getJedis();
				//为当前注册成功的用户分配一个token，放在redis中
				token =(int)((Math.random()*9+1)*100000)+"";
				jedis.hset("loginStatus", token, "");
				jedis.close();
			dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
			dataWrapper.setData(user);
			dataWrapper.setMsg(token);
			System.out.println(dataWrapper);
		}
		
		return dataWrapper;
	}


	public DataWrapper<Users> login(String userName, String passWord) {
		DataWrapper<Users> dataWrapper=new DataWrapper<Users>();
		Jedis  jedis=RedisClient.getInstance().getJedis();
		
		//检测用户账号密码是否正确
		Users user=regLoginDao.queryPassWordIsOk(userName,passWord);
		if(user==null){
			dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			dataWrapper.setMsg("账号或者密码错误");
			return dataWrapper;
			
		}else{
			String  token=null;
			try {
				//为当前注册成功的用户分配一个token，放在redis中
				token =(int)((Math.random()*9+1)*100000)+"";
				jedis.hset("loginStatus", token, "");
			} finally {
				if(jedis != null){
					jedis.close();
				}
			}
			dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
			dataWrapper.setData(user);
			dataWrapper.setMsg(token);
		}
		return dataWrapper;
	}

   
	public DataWrapper<Void> logout(String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		Jedis  jedis=RedisClient.getInstance().getJedis();
		jedis.hdel("loginStatus", token);
		dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
		dataWrapper.setMsg("退出成功");
		return dataWrapper;
	}

}
