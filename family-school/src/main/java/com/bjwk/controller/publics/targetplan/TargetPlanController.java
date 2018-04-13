package com.bjwk.controller.publics.targetplan;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bjwk.service.publics.targetplan.TargetPlanService;

/** 
* @Description: 目标计划控制层
* @author  Desolation
* @email:1071680460@qq.com
* @date 创建时间：2018年3月27日 上午12:10:08 
* @version 1.0  
*/

@Controller
@RequestMapping("api/plan")
public class TargetPlanController {
	
	private static final Log _logger = LogFactory.getLog(TargetPlanController.class);
	
	@Autowired
	private TargetPlanService targetPlanService;
	
	
	/**
	 * 设置计划安排接口
	 * @param request
	 * @param userName  [用户名]
	 * @param planName [计划名称]
	 * @param planContent [计划内容]
	 * @param dateTime [计划时间]
	 * @param type [计划类型]  1:天 2:周  3：月  4：年  
	 */
	@RequestMapping("setPlan")
	@ResponseBody
	public JSONObject setPlan(@RequestBody JSONObject request){
		_logger.info("设置计划安排");
		String userName = request.getString("userName");
		String planName  = request.getString("planName");
		String planContent  =request.getString("planContent");
		String dateTime = request.getString("dateTime");
		int type = request.getInteger("type");
		_logger.info("接收到所需要设置的计划信息为：用户为"+userName+"计划类型为："+type+",计划名称为："+planName+",计划内容为:"+planContent+",时间为："+dateTime);
		
		return targetPlanService.setPlan(request);
	}
	
	
	/**
	 * 获取目标计划
	 * @param request
	 * @return
	 */
	@RequestMapping("getPlan")
	@ResponseBody
	public JSONObject getPlan(@RequestBody JSONObject request){
		_logger.info("获取时间计划安排");
		String userName = request.getString("userName");
		String dateTime = request.getString("dateTime");
		int type = request.getInteger("type");
		_logger.info("获取到时间计划的信息为：当前用户："+userName+",类型为："+type+",时间为："+dateTime);
		
		return targetPlanService.getPlan(request);
	}
	
	
}
