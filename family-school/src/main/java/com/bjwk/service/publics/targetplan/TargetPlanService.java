package com.bjwk.service.publics.targetplan;

import com.alibaba.fastjson.JSONObject;

/** 
* @Description: 时间计划实现接口
* @author  Desolation
* @email:1071680460@qq.com
* @date 创建时间：2018年3月27日 下午11:16:26 
* @version 1.0  
*/
public interface TargetPlanService {
	
	public JSONObject getPlan(JSONObject request);
	
	public JSONObject  setPlan(JSONObject request);

}
