package com.bjwk.controller.publics.reglogin;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjwk.model.pojo.Users;
import com.bjwk.service.publics.reglogin.RegLoginService;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.RedisClient;

import redis.clients.jedis.Jedis;


/**
 * @author liqitian
 * @describe  用户登录注册（教师，学生，家长，管理员）
 * @date 2018年2月27日 下午8:46:29 
 * @version 1.0
 */
@Controller
@RequestMapping("api/regLogin")
public class RegLoginController {
	
	private static final Log _logger = LogFactory.getLog(RegLoginController.class);

	@Autowired  
	private RegLoginService regLoginService;
	
	/**
	 * @param user
	 * @return
	 * @describe 用户注册
	 */
	@RequestMapping(value="_reg")
	@ResponseBody
	public DataWrapper<Users> insertReg(@ModelAttribute(value="user") Users user,
			@RequestParam(value="code",required=true) String code
			){
		
		_logger.info("进入用户注册....");
		return regLoginService.insertReg(user,code);
	}
	
	/**
	 * @param user
	 * @return
	 * @describe 账号密码用户登录
	 */
	@RequestMapping(value="_login")
	@ResponseBody
	public DataWrapper<Users> login(
			@RequestParam(value="userName",required=true)String userName,
			@RequestParam(value="passWord",required=true)String passWord
			){
		_logger.info("用户账号登录...");
		return regLoginService.login(userName,passWord);
	}
	
	/**
	 * 
	 * @param sign
	 * @param phone
	 * @param code
	 * @return Users
	 */
	@RequestMapping(value="_phoneVcodeLogin")
	@ResponseBody
	public DataWrapper<Users> phoneVcodeLogin(
			@RequestParam(value="sign",required=true)Integer sign,
			@RequestParam(value="phone",required=true)String phone,
			@RequestParam(value="code",required=true)String code
			){
		_logger.info("手机验证码登录...");
		return regLoginService.phoneVcodeLogin(phone,code,sign);
	}
	/**
	 * @param user
	 * @return
	 * @describe 手势密码用户登录
	 */
	@RequestMapping(value="_gestureLogin")
	@ResponseBody
	public DataWrapper<Users> gestureLogin(
			@RequestParam(value="token",required=true)String token,
			@RequestParam(value="gesturePassWord",required=true)String gesturePassWord
			){
		_logger.info("用户登录...");

		return regLoginService.gestureLogin(token,gesturePassWord);
	}

	/**
	 * 
	 * @param user
	 * @return
	 * @describe 用户登出
	 */
	@RequestMapping(value="_logout")
	@ResponseBody
	public DataWrapper<Void> logout(
			@RequestParam(value="token",required=true)String token
			){
		_logger.info("用户登出...");
		return regLoginService.logout(token);
	}
	/**
	 * 
	 * @param headPortrait
	 * @param sex
	 * @param professionId
	 * @param background
	 * @param styleSignTure
	 * @return
	 */
	@RequestMapping(value="_changeUserInfo")
	@ResponseBody
	public DataWrapper<Void> changeUserInfo(
			@RequestParam(value="token",required=false)String token,
			@RequestParam(value="headPortrait",required=false)String headPortrait,
			@RequestParam(value="sex",required=false)String sex,
			@RequestParam(value="nickName",required=false) String nickName,
			@RequestParam(value="professionId",required=false)String professionId,
			@RequestParam(value="background",required=false)String background,
			@RequestParam(value="styleSignTure",required=false)String styleSignTure
			){
		_logger.info("用户更改个人信息...");
		System.out.println(styleSignTure);//Connector标签增加useBodyEncodingForURI="true"
		return regLoginService.changeUserInfo(token,headPortrait,sex,professionId,background,styleSignTure
				,nickName);
	}
	
	@RequestMapping(value="_userUpdateToPassWord")
	@ResponseBody
	public DataWrapper<Void> userUpdateToPassWord(
			@RequestParam(value="sign",required=false)String sign,
			@RequestParam(value="phone",required=false)String phone,
			@RequestParam(value="code",required=false)String code
			){
		_logger.info("用户更改个人密码...");
		return regLoginService.userUpdateToPassWord(sign,phone,code);
	}
	/**
	 *
	 * @param user
	 * @return
	 * @describe 管理员修改用户
	 */
	@RequestMapping(value="_updateUser")
	@ResponseBody
	public DataWrapper<Void> _updateUser(
//			@RequestParam(value="token",required=true)String token
			){
		int i=1;
		int x=2;
		i=x/0;
		return null;
	}
	@RequestMapping("_test")
	@ResponseBody
	public DataWrapper<Void> test(
			@RequestParam(value="token",required=false)String token
			){
		DataWrapper<Void>  dataWrapper=new DataWrapper<Void>();
		Jedis jedis=RedisClient.getInstance().getJedis();
		if(jedis.hget("loginStatus", token)==null) {
			dataWrapper.setMsg("请重新登录");
			return dataWrapper;
		}
		dataWrapper.setMsg("1234");
		return  dataWrapper;
	}
}