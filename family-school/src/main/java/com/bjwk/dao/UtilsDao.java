package com.bjwk.dao;

import org.apache.ibatis.annotations.Select;

/** 
* @Description: TODO
* @author  Desolation
* @email:1071680460@qq.com
* @date 创建时间：2018年2月26日 下午11:50:28 
* @version 1.0  
*/
public interface UtilsDao {
    @Select("select sign from users where user_name=#{userName}")
	public Integer queryAdminUserSign(String userName);

}
