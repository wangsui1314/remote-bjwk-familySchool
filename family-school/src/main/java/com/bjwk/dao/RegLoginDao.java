package com.bjwk.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.bjwk.model.pojo.Users;
@Component
public interface RegLoginDao {

	int insertReg(Users user);
    String queryUserIsTrue(String userName);
    
    
	Users queryPassWordIsOk(@Param("userName")String userName, @Param("passWord")String passWord);
	
	Users gestureLogin(@Param("userName")String userName, @Param("gesturePassWord")String gesturePassWord);
}
