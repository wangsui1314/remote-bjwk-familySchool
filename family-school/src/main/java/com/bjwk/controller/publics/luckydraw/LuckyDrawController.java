package com.bjwk.controller.publics.luckydraw;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bjwk.service.publics.luckydraw.LuckyDrawService;


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
	@Autowired
	private LuckyDrawService luckyDrawService;
	
	/**
	 * 设置用户抽奖信息，包括奖品等
	 * @param request
	 * @param
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping("setLuckyDrawInfo")
	@ResponseBody
	public JSONObject setLuckyDrawInfo(@RequestBody JSONObject request){
		_logger.info("设置用户抽奖信息");
		String prizeName  = request.getString("prizeName");
		String prizeGrade = request.getString("prizeGrade");
		String prizeProbability = request.getString("prizeProbability");
		_logger.info("设置的用户抽奖信息实现类的信息为：奖项名称："+prizeName+",奖项等级："+prizeGrade+",奖项概率："+prizeProbability);
		return luckyDrawService.setLuckyDrawInfo(request);
	}
	
	
	/**
	 * 获取用户抽奖信息
	 * @param request
	 * @return
	 */
	@RequestMapping("getLuckyDrawInfo")
	@ResponseBody
	public JSONObject getLuckyDrawInfo(@RequestBody JSONObject request){
		_logger.info("获取用户抽奖信息");
		return luckyDrawService.getLuckyDrawInfo();
	}

}
