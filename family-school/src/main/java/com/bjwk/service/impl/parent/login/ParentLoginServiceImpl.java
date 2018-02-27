package com.bjwk.service.impl.parent.login;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.bjwk.service.parent.login.ParentLoginService;

/** 
* @Description: 家长端登录、注册实现类
* @author  Desolation
* @email:1071680460@qq.com
* @date 创建时间：2018年2月26日 下午8:44:19 
* @version 1.0  
*/
@Service
public class ParentLoginServiceImpl implements ParentLoginService {
	
	private static final Log _logger = LogFactory.getLog(ParentLoginServiceImpl.class);
	
	/**
	 * 校验家长端登录
	 * @param parent_name 家长登录名
	 * @param parent_passwd 家长登录密码
	 * @return
	 */
	public boolean checkLogin(String parent_name, String parent_passwd) {
		_logger.info("校验家长端登录信息,传递过来的信息为:parent_name:"+parent_name+",parent_passwd:"+parent_passwd);
		
		return false;
	}

	/**
	 * 家长端用户注册
	 * @param parent_name 家长注册账号
	 * @param parent_passwd 家长注册密码
	 * @param parent_email 家长端注册邮箱
	 * @return
	 */
	public boolean addLogin(String parent_name, String parent_passwd, String parent_email) {
		return false;
	}

}
