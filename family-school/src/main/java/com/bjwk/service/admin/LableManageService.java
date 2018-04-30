package com.bjwk.service.admin;

import java.util.HashMap;
import java.util.List;

import com.bjwk.utils.DataWrapper;

public interface LableManageService {

	DataWrapper<Void> insertLable(String adminToken, String labeName,String slableType);

	DataWrapper<Void> deleteLable(String adminToken, int lableId);

	DataWrapper<List<HashMap<String, Object>>> querLables();

}
