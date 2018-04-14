package com.bjwk.dao;

import java.util.List;
import java.util.Map;

import com.bjwk.model.LuckyDraw;

/** 
* @Description: 用户抽奖dao层
* @author  Desolation
* @email:1071680460@qq.com
* @date 创建时间：2018年4月2日 下午10:56:52 
* @version 1.0  
*/
public interface LuckyDrawDao {
	
	int insertPrizeInfo(LuckyDraw luckyDraw);
	
	List<Map<String, Object>> selectPrizeInfo();

}
