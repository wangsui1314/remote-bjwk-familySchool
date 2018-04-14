package com.bjwk.controller.publics.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bjwk.service.publics.communication.GroupService;
import com.bjwk.utils.DataWrapper;

/**
 * 
 * @ClassName: GroupController 
 * @Description: TODO("群组操作") 
 * @author: lihui 
 * @date: 2018年3月31日 上午2:04:15
 */
@Controller
@RequestMapping("api/common/group")
public class GroupController {

	@Autowired
	private GroupService groupServcie;
	
	/**
	 * 
	* @Title: createGroup 
	* @Description: TODO(创建群组) 
	* @param @param token
	* @param @param groupName
	* @param @param type
	* @param @return
	* @return DataWrapper<Void>    返回类型 
	* @throws
	 */
	public DataWrapper<Void> createGroup(
			@RequestParam(value="token",required=true) String token,
			@RequestParam(value="groupName",required=true) String groupName,
			@RequestParam(value="type",required=false) String type  //群组类型  待定
			){
		
		return groupServcie.cerateGroup(token,groupName,type);
	}
}
