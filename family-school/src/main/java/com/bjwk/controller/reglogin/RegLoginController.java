package com.bjwk.controller.reglogin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjwk.model.pojo.Users;
import com.bjwk.service.RegLoginService;
import com.bjwk.utils.DataWrapper;


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
	public DataWrapper<Users> insertReg(@ModelAttribute(value="user") Users user){
		
		_logger.info("进入用户注册....");
		
		return regLoginService.insertReg(user);
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
		_logger.info("用户登录...");

		return regLoginService.login(userName,passWord);
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
	 * @param user
	 * @return
	 * @describe 管理员修改用户
	 */
	@RequestMapping(value="_updateUser")
	@ResponseBody
	public DataWrapper<Void> _updateUser(
			@RequestParam(value="token",required=true)String token
			){
		return null;
	}
}
