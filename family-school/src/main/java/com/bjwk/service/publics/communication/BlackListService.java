package com.bjwk.service.publics.communication;

import com.bjwk.utils.DataWrapper;
import com.bjwk.zrongcloud.io.rong.models.response.BlackListResult;

public interface BlackListService {

	DataWrapper<Void> addBlack(String token, String blackUserId);

	DataWrapper<Void> removeBlack(String token, String blackUserId);

	DataWrapper<BlackListResult> queryBlack(String token);

}
