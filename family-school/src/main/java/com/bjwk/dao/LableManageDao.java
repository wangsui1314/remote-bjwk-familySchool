package com.bjwk.dao;

import java.util.HashMap;

import com.bjwk.utils.DataWrapper;

public interface LableManageDao {

	int insertLable(String labeName);
	
	int deleteLable(int lableId);
	int deleteLableToUserLable(int lableId);

	HashMap<String, Object> querLables();
}
