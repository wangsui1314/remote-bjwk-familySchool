package com.bjwk.controller.reglogin;

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
 * @version 1.0
 * @describe  用户登录注册（教师，学生，家长，管理员）
 * 2018年2月27日 下午8:46:29 
 */
@Controller
@RequestMapping("shq/regLogin")
public class RegLoginController {
	
	@Autowired  
	private RegLoginService regLoginService;
	/**
	 * 
	 * @param user
	 * @return
	 * @describe 用户注册
	 */
	@RequestMapping(value="_reg")
	@ResponseBody
	public DataWrapper<Users> reg(
			@ModelAttribute(value="user") Users user
			){
		
		return regLoginService.reg(user);
	}
	/**
	 * 
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
		return regLoginService.logout(token);
	}
}
