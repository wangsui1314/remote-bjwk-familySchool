package com.bjwk.service.publics.reglogin;

import com.bjwk.model.pojo.Users;
import com.bjwk.utils.DataWrapper;

public interface RegLoginService {

	DataWrapper<Users> insertReg(Users user,String code);

	DataWrapper<Users> login(String userName, String passWord);

	DataWrapper<Void> logout(String token);

	DataWrapper<Users> gestureLogin(String gesturePassWord, String gesturePassWord2);

	DataWrapper<Users> phoneVcodeLogin(String phone, String code, Integer sign);

	DataWrapper<Void> changeUserInfo(String token,String headPortrait, String sex, String professionId, String background,
			String styleSignTure);

	DataWrapper<Void> userUpdateToPassWord(String sign, String phone, String code);

}
