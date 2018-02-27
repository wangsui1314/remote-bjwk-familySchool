package com.bjwk.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.bjwk.model.pojo.Users;

public interface RegLoginDao {

	int reg(Users user);
    String queryUserIsTrue(String userName);
    
    
	Users queryPassWordIsOk(@Param("userName")String userName, @Param("passWord")String passWord);
}
