package com.bjwk.controller.publics.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjwk.service.publics.communication.SensitiveWordService;
import com.bjwk.utils.DataWrapper;
import com.bjwk.zrongcloud.io.rong.models.response.ListWordfilterResult;
/**
 * 
 * @ClassName: SensitiveWordController 
 * @Description: TODO("敏感词汇") 
 * @author: lihui 
 * @date: 2018年3月31日 上午1:14:43
 * 敏感词最多设置50个
 * 敏感词替换目前只支持单聊、群聊、聊天室会话。
 */
@Controller
@RequestMapping("api/common/sensitive")
public class SensitiveWordController {
  
	@Autowired
	private SensitiveWordService sensitiveWordService;
	
	/**
	 * 
	* @Title: addSensWord 
	* @Description: TODO(添加敏感词) 
	* @param @param adminToken
	* @param @param word
	* @param @param replaceWord
	* @param @return    设定文件 
	* @return DataWrapper<Void>    返回类型 
	* @throws
	 */
	@RequestMapping("addSensWord")
	@ResponseBody
	public DataWrapper<Void>  addSensWord(
			@RequestParam(value="adminToken",required=false) String adminToken,	
			@RequestParam(value="word",required=true) String word,
			@RequestParam(value="replaceWord",required=true) String replaceWord	
			){
		return sensitiveWordService.addSensWord(adminToken,word,replaceWord);
	}
	
	/**
	 * 
	* @Title: removeSensWord 
	* @Description: TODO(移除敏感词) 
	* @param @param adminToken
	* @param @param word
	* @param @return    设定文件 
	* @return DataWrapper<Void>    返回类型 
	* @throws
	 */
	@RequestMapping("removeSensWord")
	@ResponseBody
	public DataWrapper<Void>  removeSensWord(
			@RequestParam(value="adminToken",required=false) String adminToken,	
			@RequestParam(value="word",required=true) String word
			){
		return sensitiveWordService.removeSensWord(adminToken,word);
	}
	
	@RequestMapping("querySensWordList")
	@ResponseBody
	public DataWrapper<ListWordfilterResult>  querySensWordList(
			@RequestParam(value="adminToken",required=false) String adminToken,	
			//查询敏感词的类型，0 为查询替换敏感词，1 为查询屏蔽敏感词，2 为查询全部敏感词。默认为 1。（非必传）
			@RequestParam(value="type",required=false,defaultValue="1") String type
			){
		return sensitiveWordService.querySensWordList(adminToken,type);
	}
}
