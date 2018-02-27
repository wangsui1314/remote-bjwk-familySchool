package com.bjwk.service;

import com.bjwk.model.pojo.Users;
import com.bjwk.utils.DataWrapper;

public interface RegLoginService {

	DataWrapper<Users> reg(Users user);

	DataWrapper<Users> login(String userName, String passWord);

	DataWrapper<Void> logout(String token);

}
