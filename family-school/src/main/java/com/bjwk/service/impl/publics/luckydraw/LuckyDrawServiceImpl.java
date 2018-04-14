package com.bjwk.service.impl.publics.luckydraw;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bjwk.service.publics.luckydraw.LuckyDrawService;

/** 
* @Description: 用户抽奖实现类
* @author  Desolation
* @email:1071680460@qq.com
* @date 创建时间：2018年3月27日 下午11:51:00 
* @version 1.0  
*/
@Service
public class LuckyDrawServiceImpl implements LuckyDrawService{
	
	private static final Log _logger = LogFactory.getLog(LuckyDrawServiceImpl.class);

	@Override
	public JSONObject setLuckyDrawInfo(JSONObject request) {
		_logger.info("设置用户抽奖信息实现类");
		String prizeName  = request.getString("prizeName");
		String prizeGrade = request.getString("prizeGrade");
		String prizeProbability = request.getString("prizeProbability");
		_logger.info("设置的用户抽奖信息实现类的信息为：奖项名称："+prizeName+",奖项等级："+prizeGrade+",奖项概率："+prizeProbability);
		return null;
	}

	@Override
	public JSONObject getLuckyDrawInfo() {
		_logger.info("获取用户抽奖信息实现类");
		return null;
	}

}
