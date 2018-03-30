package com.bjwk.controller.publics.communication;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjwk.service.publics.communication.MessageSentService;
import com.bjwk.utils.DataWrapper;

/**
 * 
 * @ClassName: MessageSentController 
 * @Description: TODO("消息发送服务") 
 * @author: lihui 
 * @date: 2018年3月30日 下午10:38:04
 */
@Controller
@RequestMapping("api/comm/message")
public class MessageSentController {

	@Autowired
	private MessageSentService messageSentService;
	
	/**
	 * 
	* @Title: singleTxtMsgChat 
	* @Description: TODO(发送单聊消息) 此方法待定 
	* @param @param token
	* @param @param targetIds
	* @param @param objectName
	* @param @param content
	* @param @return    设定文件 
	* @return DataWrapper<Object>    返回类型 
	* @throws
	 */
	@RequestMapping("sentMsg")
	@ResponseBody
	public DataWrapper<Object> singleTxtMsgChat(
			@RequestParam(value="token",required=true) String token,
			@RequestParam(value="targetId",required=true) String[] targetIds,
			@RequestParam(value="objectName",required=false) String objectName,
			@RequestParam(value="content",required=true) String content
			){
		return messageSentService.singleTxtMsgChat(token,targetIds,objectName,content);
	}
}
