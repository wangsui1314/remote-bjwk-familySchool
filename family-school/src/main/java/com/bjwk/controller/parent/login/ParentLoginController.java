package com.bjwk.controller.parent.login;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bjwk.service.parent.login.ParentLoginService;

/** 
* @Description: 家长端登录、注册等功能
* @author  Desolation
* @email:1071680460@qq.com
* @date 创建时间：2018年2月26日 下午8:30:07 
* @version 1.0  
*/
@Controller
@RequestMapping("parentLogin")
public class ParentLoginController {
	
	private Log _logger = LogFactory.getLog(ParentLoginController.class);
	
	@Autowired
	private ParentLoginService parentLoginService;
	
	/**
	 * 家长端登录
	 * @param request
	 * @return
	 */
	@RequestMapping(value="_login")
	@ResponseBody
	public String login(@RequestBody JSONObject request){
		_logger.info("家长端登录接口");
		String parent_name = request.getString("parent_name");
		String parent_passwd = request.getString("parent_passwd");
		_logger.info("当前用户登录的信息为：parent_name:"+parent_name+",parent_passwd:"+parent_passwd);
		//校验用户信息
		boolean b = parentLoginService.checkLogin(parent_name,parent_passwd);
		if(b){
			return "返回身份令牌";
		}
		return null;
	}
	
	
	/**
	 * 家长端注册
	 * @param request
	 * @return
	 */
	@RequestMapping(value="_regLogin")
	@ResponseBody
	public String regLogin(@RequestBody JSONObject request){
		_logger.info("家长端注册接口");
		String parent_name = request.getString("parent_name");
		String parent_passwd = request.getString("parent_passwd");
		String parent_email = request.getString("parent_email");
		_logger.info("当前家长端注册的信息为：parent_name:"+parent_name+",parent_passwd:"+parent_passwd+",parent_email"+parent_email);
		//校验用户信息
		boolean b = parentLoginService.addLogin(parent_name,parent_passwd,parent_email);
		if(b){
			return "返回身份令牌";
		}
		return null;
	}
	

}