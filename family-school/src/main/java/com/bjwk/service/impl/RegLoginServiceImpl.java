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


	public DataWrapper<Users> insertReg(Users user) {
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
		if(regLoginDao.insertReg(user)!=0){
			Jedis jedis=null;
			String token=null;

			jedis=RedisClient.getInstance().getJedis();
			//为当前注册成功的用户分配一个token，放在redis中
			token =(int)((Math.random()*9+1)*100000)+"";
			jedis.hset("loginStatus", token, user.getUserName());
			jedis.close();
			dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
			dataWrapper.setData(user);
			dataWrapper.setToken(token);
			dataWrapper.setMsg("注册成功");
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
				jedis.hset("loginStatus", token, userName);
			} finally {
				if(jedis != null){
					jedis.close();
				}
			}
			dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
			dataWrapper.setData(user);
			dataWrapper.setToken(token);
			dataWrapper.setMsg("登录成功");
		}
		return dataWrapper;
	}


	public DataWrapper<Void> logout(String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		Jedis  jedis=RedisClient.getInstance().getJedis();
		long state=jedis.hdel("loginStatus", token);
		if(state==0){
			dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			dataWrapper.setMsg("退出失败");
			return dataWrapper;
		}
		dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
		dataWrapper.setMsg("退出成功");
		return dataWrapper;
	}

}
