package com.bjwk.controller.publics.luckydraw;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;


/** 
* @Description: 用户抽奖
* @author  Desolation
* @email:1071680460@qq.com
* @date 创建时间：2018年3月27日 下午11:39:55 
* @version 1.0  
*/

@Controller
@RequestMapping("api/luckyDraw")
public class LuckyDrawController {
	
	private static final Log _logger = LogFactory.getLog(LuckyDrawController.class);
	
	@RequestMapping("setLuckyDrawInfo")
	@ResponseBody
	public JSONObject setLuckyDrawInfo(@RequestBody JSONObject request){
		_logger.info("设置用户抽奖信息");
		return null;
	}
	
	@RequestMapping("getLuckyDrawInfo")
	@ResponseBody
	public JSONObject getLuckyDrawInfo(@RequestBody JSONObject request){
		_logger.info("设置用户抽奖信息");
		return null;
	}
	

}
