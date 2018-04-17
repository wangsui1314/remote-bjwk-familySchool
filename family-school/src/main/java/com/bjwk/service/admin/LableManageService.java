package com.bjwk.service.admin;

import java.util.HashMap;

import com.bjwk.utils.DataWrapper;

public interface LableManageService {

	DataWrapper<Void> insertLable(String adminToken, String labeName);

	DataWrapper<Void> deleteLable(String adminToken, int lableId);

	DataWrapper<HashMap<String, Object>> querLables();

}
