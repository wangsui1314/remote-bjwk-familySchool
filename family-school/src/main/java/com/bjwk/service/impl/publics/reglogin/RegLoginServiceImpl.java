package com.bjwk.service.impl.publics.reglogin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjwk.dao.RegLoginDao;
import com.bjwk.model.pojo.Users;
import com.bjwk.service.publics.reglogin.RegLoginService;
import com.bjwk.utils.CallStatusEnum;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.ErrorCodeEnum;
import com.bjwk.utils.RedisClient;
import com.bjwk.utils.sms.VerifiCodeValidateUtil;

import redis.clients.jedis.Jedis;



@Service
public class RegLoginServiceImpl  implements RegLoginService{
	private static final Log _logger = LogFactory.getLog(RegLoginServiceImpl.class);
	@Autowired
	private RegLoginDao regLoginDao;


	public DataWrapper<Users> insertReg(Users user,String code) {
		_logger.info("注册实现，接收到的数据为：User"+user.toString());
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
		ErrorCodeEnum codeEnum= VerifiCodeValidateUtil.verifiCodeValidate(user.getPhone(),code);
		if(!codeEnum.equals(ErrorCodeEnum.No_Error)){
			dataWrapper.setErrorCode(codeEnum);
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
		_logger.info("用户登录实现，接收到的数据为：userName="+userName+",passWord="+passWord);
		DataWrapper<Users> dataWrapper=new DataWrapper<Users>();

		//检测用户账号密码是否正确
		Users user=regLoginDao.queryPassWordIsOk(userName,passWord);
		if(user==null){
			dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			dataWrapper.setMsg("账号或者密码错误");
			return dataWrapper;

		}
		Jedis  jedis=RedisClient.getInstance().getJedis();
		/**
		 * 挤掉策略
		 * 1 判断用户是否在线
		 */
		String isOnLine=jedis.hget("statusLogin", userName);
		String newToken=(int)((Math.random()*9+1)*100000)+"";
		if(isOnLine!=null){
			//使已在线用户下线
			jedis.hdel("loginStatus", isOnLine)	;
			jedis.hdel("statusLogin", userName)	;
			jedis.hset("loginStatus", newToken, userName);
			jedis.hset("statusLogin", userName, newToken);
			jedis.close();
			dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
			dataWrapper.setData(user);
			dataWrapper.setToken(newToken);
			dataWrapper.setMsg("登录成功");
			return dataWrapper;
		}
		String  token=null;
		try {
			//为当前注册成功的用户分配一个token，放在redis中
			token =(int)((Math.random()*9+1)*100000)+"";
			_logger.info("当前用户："+userName+",分配的token为："+token);
			jedis.hset("loginStatus", token, userName);
			jedis.hset("statusLogin", userName, token);
		} finally {
			if(jedis != null){
				jedis.close();
			}
		}
		dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
		dataWrapper.setData(user);
		dataWrapper.setToken(token);
		dataWrapper.setMsg("登录成功");

		return dataWrapper;
	}
	//手势密码登录
	@Override
	public DataWrapper<Users> gestureLogin(String token,String gesturePassWord) {
		// TODO Auto-generated method stub
		//根据toekn获取用户名
		DataWrapper<Users> dataWrapper=new DataWrapper<Users>();
		Jedis  jedis=RedisClient.getInstance().getJedis();
		String userName=jedis.hget("loginStatus", token);

		/*
		 *  userName  和  gesturePassWord 入库检测 is True
		 */
		Users user=regLoginDao.gestureLogin(userName,gesturePassWord);
		if(user==null){
			dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			dataWrapper.setMsg("手势密码错误");
		}else{
			dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
			dataWrapper.setData(user);
		}
		return dataWrapper;
	}
	/**
	 * 登出
	 */
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

	/**
	 * 验证码登录
	 */
	@Override
	public DataWrapper<Users> phoneVcodeLogin(String phone, String code, Integer sign) {
		// TODO Auto-generated method stub
		DataWrapper<Users> dataWrapper=new DataWrapper<Users>();

		ErrorCodeEnum codeEnum= VerifiCodeValidateUtil.verifiCodeValidate(phone,code);
		if(!codeEnum.equals(ErrorCodeEnum.No_Error)){
			dataWrapper.setErrorCode(codeEnum);
			return dataWrapper;
		}
		Users user=regLoginDao.validaIsTrueByPhoneAndSign(phone,sign);
		//检测用户账号密码是否正确
		if(user==null){
			dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			dataWrapper.setMsg("账号或者密码错误");
			return dataWrapper;

		}
		Jedis  jedis=RedisClient.getInstance().getJedis();
		/**
		 * 挤掉策略
		 * 1 判断用户是否在线
		 */
		String userName =user.getUserName();
		String isOnLine=jedis.hget("statusLogin", userName);
		String newToken=(int)((Math.random()*9+1)*100000)+"";
		if(isOnLine!=null){
			//使已在线用户下线
			jedis.hdel("loginStatus", isOnLine)	;
			jedis.hdel("statusLogin", userName)	;
			jedis.hset("loginStatus", newToken, userName);
			jedis.hset("statusLogin", userName, newToken);
			jedis.close();
			dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
			dataWrapper.setData(user);
			dataWrapper.setToken(newToken);
			dataWrapper.setMsg("登录成功");
			return dataWrapper;
		}
		String  token=null;
		try {
			//为当前注册成功的用户分配一个token，放在redis中
			token =(int)((Math.random()*9+1)*100000)+"";
			_logger.info("当前用户："+userName+",分配的token为："+token);
			jedis.hset("loginStatus", token, userName);
			jedis.hset("statusLogin", userName, token);
		} finally {
			if(jedis != null){
				jedis.close();
			}
		}
		dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
		dataWrapper.setData(user);
		dataWrapper.setToken(token);
		dataWrapper.setMsg("登录成功");

		return dataWrapper;
		
	}

	/**
	 * 更改个人信息
	 */
	@Override
	public DataWrapper<Void> changeUserInfo(String token,String headPortrait, String sex, String professionId, String background,
			String styleSignTure) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();

		Jedis  jedis=RedisClient.getInstance().getJedis();
		String userName=jedis.hget("loginStatus", token);
		if(userName==null){
			dataWrapper.setMsg("令牌错误");
			dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			return dataWrapper;
		}

		int state=regLoginDao.changeUserInfo(headPortrait,sex,professionId,background,styleSignTure,userName);
		if(state!=0){
			dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
			return dataWrapper;
		}
		dataWrapper.setCallStatus(CallStatusEnum.FAILED);
		return dataWrapper;
	}

	/**
	 * 用户更改个人密码
	 * (暂时须有不明确 待定)
	 */
	@Override
	public DataWrapper<Void> userUpdateToPassWord(String sign, String phone,String code) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		Jedis  jedis=RedisClient.getInstance().getJedis();

		ErrorCodeEnum codeEnum= VerifiCodeValidateUtil.verifiCodeValidate(phone,code);
		if(!codeEnum.equals(ErrorCodeEnum.No_Error)){
			dataWrapper.setErrorCode(codeEnum);
			return dataWrapper;
		}
		//修改
		int state=regLoginDao.updateUserPassWord(sign,phone);

		//String userName=regLoginDao.getUserNameByPhoneAndSign(sign,phone);
		//String token=jedis.hget("statusLogin", userName);
		//long state=jedis.hdel("loginStatus", token);  
		return null;
	}
}
