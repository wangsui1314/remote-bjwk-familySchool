package com.bjwk.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bjwk.model.NearUsers;

public interface NearManDao {

	
	List<NearUsers> queryNearMan(@Param("nearUserList")List<String> nearUserList,
			@Param("sex")String lat,@Param("array")String[] others);


}
