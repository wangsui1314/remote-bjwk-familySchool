package com.bjwk.service.parent.login;
/** 
* @Description: 家长端登录、注册实现类接口
* @author  Desolation
* @email:1071680460@qq.com
* @date 创建时间：2018年2月26日 下午8:42:59 
* @version 1.0  
*/
public interface ParentLoginService {
	
	/**
	 * 校验家长端登录
	 * @param parent_name 家长登录名
	 * @param parent_passwd 家长登录密码
	 * @return
	 */
	public boolean checkLogin(String parent_name,String parent_passwd);
	
	/**
	 * 家长端用户注册
	 * @param parent_name 家长注册账号
	 * @param parent_passwd 家长注册密码
	 * @param parent_email 家长端注册邮箱
	 * @return
	 */
	public boolean addLogin(String parent_name,String parent_passwd,String parent_email);

}
