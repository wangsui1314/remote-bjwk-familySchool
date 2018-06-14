package com.bjwk.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.bjwk.model.pojo.Users;

public interface RegLoginDao {

	int insertReg(Users user);
    String queryUserIsTrue(@Param("userName")String userName,@Param("sign")String sign);
    
    
	Users queryPassWordIsOk(@Param("userName")String userName, @Param("passWord")String passWord,@Param("sign")String sign);
	
	Users gestureLogin(@Param("userName")String userName, @Param("gesturePassWord")String gesturePassWord);
	
	Users validaIsTrueByPhoneAndSign(@Param("phone")String phone, @Param("sign")Integer sign);
	
	
	int changeUserInfo(@Param("headPortrait")String headPortrait, @Param("sex")String sex,
			@Param("nickName") String nickName,
			@Param("background")String background, @Param("styleSignTure")String styleSignTure,
			@Param("userName")String userName);
	
	String userUpdateToPassWordCheck(@Param("sign")Integer sign, @Param("phone")String phone);
	
	String getUserIdByUserName(@Param("userName")String userName);
	
	
	int insrtLable(@Param("userId")String userId, @Param("array")String[] split);
	
	Users  queryUserInfoDetails(@Param("userName")String userName,@Param("sign") Integer sign);

	int queryUserIsTrueByPhoneSign(@Param("phone")String phone, @Param("sign")String sign);

	int userUpdateToPassWord(@Param("userName")String param, @Param("sign")String param1, @Param("newPassWd")String newPassWd);
}
