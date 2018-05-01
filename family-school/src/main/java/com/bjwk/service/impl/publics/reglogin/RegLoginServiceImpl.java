package com.bjwk.service.impl.publics.reglogin;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.bjwk.dao.RegLoginDao;
import com.bjwk.model.pojo.Users;
import com.bjwk.service.publics.reglogin.RegLoginService;
import com.bjwk.utils.BeanUtil;
import com.bjwk.utils.CallStatusEnum;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.ErrorCodeEnum;
import com.bjwk.utils.RedisClient;
import com.bjwk.utils.sms.VerifiCodeValidateUtil;

import com.bjwk.zrongcloud.io.RongCloudKeyAndSecret;
import com.bjwk.zrongcloud.io.rong.RongCloud;
import com.bjwk.zrongcloud.io.rong.methods.user.User;
import com.bjwk.zrongcloud.io.rong.models.*;
import com.bjwk.zrongcloud.io.rong.models.response.*;
import com.bjwk.zrongcloud.io.rong.models.user.UserModel;

import redis.clients.jedis.Jedis;



@Service
public class RegLoginServiceImpl  implements RegLoginService{
	private static final Log _logger = LogFactory.getLog(RegLoginServiceImpl.class);
	@Autowired
	private RegLoginDao regLoginDao;


	@Override
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
			//验证码错误
			dataWrapper.setErrorCode(codeEnum);
			return dataWrapper;
		}
		//获取默认用户昵称与性别 ...
		Map<String,String> map=getDefaultMessage(user.getUserName(),user.getPhone());
		user.setNickName(map.get("nickname"));
		user.setHeadPortrait(map.get("headPortrait"));

		/**
		 * 更改为注册成功 不是登录成功状态
		 */
		int sign=regLoginDao.insertReg (user);
		if(sign!=0){
			//获取融云rongCloudToken
			TokenResult tr=(TokenResult) getRongCloudToken(user.getUserId()+"",map.get("nickname"),map.get("headPortrait"),1);
			System.out.println(user);
			System.out.println(tr);
			if(tr!=null) {
				dataWrapper.setRongCloudToken(tr.getToken());
			}
			dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
			dataWrapper.setData(user);
			dataWrapper.setMsg("注册成功");
			//注册成功并在融云建立用户关系
		}
		return dataWrapper;
	}

    @Override
	public DataWrapper<Users> login(String userName, String passWord,String sign) {
		_logger.info("用户登录实现，接收到的数据为：userName="+userName+",passWord="+passWord);
		DataWrapper<Users> dataWrapper=new DataWrapper<Users>();

		//检测用户账号密码是否正确
		Users user=regLoginDao.queryPassWordIsOk(userName,passWord,sign);
		if(user==null){
			dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			dataWrapper.setMsg("账号或者密码错误");
			return dataWrapper;

		}
		Jedis  jedis=RedisClient.getJedis();
		/**
		 * 挤掉策略
		 * 1 判断用户是否在线
		 */
		//String isOnLine=jedis.hget("statusLogin", userName);
		String token=(int)((Math.random()*9+1)*100000)+"";

		//利用redis hset存在覆盖 不存在插入的特性达到挤掉

		jedis.hset("loginStatus", token, userName);
		jedis.hset("statusLogin", userName, token);
		dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
		dataWrapper.setData(user);
		dataWrapper.setToken(token);
		dataWrapper.setMsg("登录成功");

		//为当前注册成功的用户分配一个token，放在redis中
		_logger.info("当前用户："+userName+",分配的token为："+token);
		jedis.close();

		return dataWrapper;
	}
	//手势密码登录
	@Override
	public DataWrapper<Users> gestureLogin(String token,String gesturePassWord) {
		// TODO Auto-generated method stub
		//根据toekn获取用户名

		DataWrapper<Users> dataWrapper=new DataWrapper<Users>();
		Jedis  jedis=RedisClient.getJedis();

		String userName=(String) jedis.hget("loginStatus", token);

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
		jedis.close();
		return dataWrapper;
	}
	/**
	 * 登出
	 */
	public DataWrapper<Void> logout(String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		Jedis  jedis=RedisClient.getJedis();
		

		jedis.hdel("statusLogin", jedis.hget("loginStatus", token));
		long state=jedis.hdel("loginStatus", token);
		if(state==0){
			dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			dataWrapper.setMsg("退出失败");
            jedis.close();
			return dataWrapper;
		}
		dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
		dataWrapper.setMsg("退出成功");
		jedis.close();
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
		Jedis  jedis=RedisClient.getJedis();
		/**
		 * 挤掉策略
		 * 1 判断用户是否在线
		 */
		String userName =user.getUserName();
		//	String isOnLine=jedis.hget("statusLogin", userName);
		String newToken=(int)((Math.random()*9+1)*100000)+"";
		//使已在线用户下线
		jedis.hset("loginStatus", newToken, userName);
		jedis.hset("statusLogin", userName, newToken);

		dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
		dataWrapper.setData(user);
		dataWrapper.setToken(newToken);
		dataWrapper.setMsg("登录成功");
		jedis.close();
		return dataWrapper;
	}

	/**
	 * 更改个人信息
	 */
	@Override
	public DataWrapper<Void> changeUserInfo(String token,String headPortrait, String sex, String lableId, String background,
			String styleSignTure,String nickName) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();

		Jedis  jedis=RedisClient.getJedis();

		String userName=(String) jedis.hget("loginStatus", token);

		String userId=regLoginDao.getUserIdByUserName(userName);
		/**
		 * 根据 lableId设置用户标签  爱好 职业等等
		 */
		//		String userId="1";
		//		lableId="1,2,3,4";
		if(lableId!=null) {
			regLoginDao.insrtLable(userId,lableId.split(","));
		}
		//更改基本信息
		int state=regLoginDao.changeUserInfo(headPortrait,sex,nickName,background,styleSignTure,userName);
		if(state!=0){
			/**
			 * 更改rongcloud 用户信息主要包括昵称与头像
			 */
			Result res=getRongCloudToken(regLoginDao.getUserIdByUserName(userName),nickName,headPortrait,2);
			//测试 打印数据
			if(res.getCode()!=200) { System.out.println("更改rongcloud数据出错");}
			dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
			jedis.close();
			return dataWrapper;
		}
		dataWrapper.setCallStatus(CallStatusEnum.FAILED);
		jedis.close();
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
		Jedis  jedis=RedisClient.getJedis();

		ErrorCodeEnum codeEnum= VerifiCodeValidateUtil.verifiCodeValidate(phone,code);
		if(!codeEnum.equals(ErrorCodeEnum.No_Error)){
			dataWrapper.setErrorCode(codeEnum);
			return dataWrapper;
		}
		//修改
		int state=regLoginDao.updateUserPassWord(sign,phone);
		if(state>0) {
			//强制退出登录
			//String userName=regLoginDao.getUserNameByPhoneAndSign(sign,phone);
			//String token=(String) ho.get("statusLogin", userName);
			//long state=ho.delete("loginStatus", token);  
		}
		return null;
	}

	/**
	 * 用户详情
	 */
	@Override
	public DataWrapper<Users> queryUserInfoDetails(String token,Integer sign) {
		// TODO Auto-generated method stub
		DataWrapper<Users> dataWrapper=new DataWrapper<Users>();
		Jedis  jedis=RedisClient.getJedis();
		String userName=(String) jedis.hget("loginStatus", token);
		if(userName==null) {
			dataWrapper.setMsg("请登录");
			dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			jedis.close();
			return dataWrapper;
		}
		System.out.println(userName+"  "+sign);
		dataWrapper.setData(regLoginDao.queryUserInfoDetails(userName,sign));
		jedis.close();
		return dataWrapper;
	}


	/**
	 * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/user/user.html#register
	 *
	 * 
	 */
	public Result getRongCloudToken(String userId,String nickName,String headPortrait,int type) {
		try {

			RongCloud rongCloud = RongCloud.getInstance(RongCloudKeyAndSecret.key, RongCloudKeyAndSecret.secret);
			//自定义 api 地址方式
			// RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);
			User User = rongCloud.user;

			UserModel user = new UserModel()
					.setId(userId)
					.setName(nickName)
					.setPortrait(headPortrait);
			if(type==1) {
				//注册用户，生成用户在融云的唯一身份标识 Token
				TokenResult result = User.register(user);
				if(result.getCode()==200) {
					return result;
				}
			}else if(type==2) {
				//刷新用户信息方法
				Result refreshResult = User.update(user);
				return refreshResult;

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	/**
	 * 
	 * @param userName
	 * @param phone
	 * @param //sex
	 * @return  Map
	 */
	private Map<String,String> getDefaultMessage(String userName,String phone){
		Map<String,String> map=new HashMap<String,String>();
		//默认昵称策略
		int phoneLen=phone.length();

		String nickname=userName.substring(0, 3)+phone.substring(phoneLen-4, phoneLen);
		//根据用户性别设置默认头像
		String headPortrait=null;
		/**
		 * 
		 *  ellipsis some code
		 */
//		if(sex==0) {
//			//女
//			headPortrait="http://www.rongcloud.cn/images/logo.png";
//		}else {
			headPortrait="http://www.rongcloud.cn/images/logo.png";
		//}
		map.put("nickname", nickname);
		map.put("headPortrait", headPortrait);
		return map;
	}


}
