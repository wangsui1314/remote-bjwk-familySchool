package com.bjwk.dao;

import java.util.HashMap;
import java.util.List;

import com.bjwk.utils.DataWrapper;

public interface LableManageDao {

	int insertLable(String labeName,String lableType);
	
	int deleteLable(int lableId);
	int deleteLableToUserLable(int lableId);

	List<HashMap<String, Object>> querLables();
}
