package com.bjwk.controller.publics.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjwk.service.publics.communication.BlackListService;
import com.bjwk.utils.DataWrapper;
import com.bjwk.zrongcloud.io.rong.models.response.BlackListResult;

/**
 * 
 * @ClassName: BlackListController 
 * @Description: TODO("黑名单操作") 
 * @author: lihui 
 * @date: 2018年3月29日 上午12:55:09
 */
@Controller
@RequestMapping("api/commun/blackOper")
public class BlackListController {
   
	@Autowired
	private BlackListService blackListService;
	
	/**
	 * 
	* @Title: addBlack 
	* @Description: TODO(用户拉黑用户) 
	* @param @param token
	* @param @param blackUserId
	* @return DataWrapper<Void>    返回类型 
	* @throws
	 */
	@RequestMapping("addBlack")
	@ResponseBody
	public DataWrapper<Void> addBlack(
			@RequestParam(value="token",required=true) String token,
			@RequestParam(value="blackUserId",required=true) String blackUserId
			){
		return blackListService.addBlack(token,blackUserId);
	}
	
	/**
	 * 
	* @Title: removeBlack 
	* @Description: TODO(移除黑名单用户的方法) 
	* @param @param token
	* @param @param blackUserId
	* @return DataWrapper<Void>    返回类型 
	* @throws
	 */
	@RequestMapping("removeBlack")
	@ResponseBody
	public DataWrapper<Void>  removeBlack(
			@RequestParam(value="token",required=true) String token,
			@RequestParam(value="blackUserId",required=true) String blackUserId
			){
		return blackListService.removeBlack(token,blackUserId);
	}
	
	/**
	 * 
	* @Title: queryBlack 
	* @Description: TODO(查看我的黑名单) 
	* @param @param token
	* @param @return    设定文件 
	* @return DataWrapper<BlackListResult>    返回类型 
	* @throws
	 */
	@RequestMapping("queryBlack")
	@ResponseBody
	public DataWrapper<BlackListResult> queryBlack(
			@RequestParam(value="token",required=true) String token
			){
		return blackListService.queryBlack(token);
	}
}
