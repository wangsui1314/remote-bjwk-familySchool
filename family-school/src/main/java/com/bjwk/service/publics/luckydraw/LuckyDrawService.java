package com.bjwk.service.publics.luckydraw;

import com.alibaba.fastjson.JSONObject;

/** 
* @Description: 用户抽奖实现接口
* @author  Desolation
* @email:1071680460@qq.com
* @date 创建时间：2018年3月27日 下午11:50:36 
* @version 1.0  
*/
public interface LuckyDrawService {
	
	public JSONObject setLuckyDrawInfo(JSONObject request);
	
	public JSONObject getLuckyDrawInfo();

}
