package com.bjwk.controller.admin;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjwk.service.admin.LableManageService;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.annotation.AdminTokenValidate;

@Controller
@RequestMapping("api/lableManage")
public class LableManageController {
	@Autowired  
	private LableManageService lableManageService;
	
	/**
	 * @param 
	 * @return
	 * @describe 新增标签
	 */
	@RequestMapping(value="_insertLable")
	@ResponseBody
	public DataWrapper<Void> insertLable(
			@RequestParam(value="adminToken",required=false) String adminToken,
			@RequestParam(value="labeName",required=true) String labeName,
			@RequestParam(value="lableType",required=true) String lableType
			){
		
		return lableManageService.insertLable(adminToken,labeName, lableType);
	}
	/**
	 * @param 
	 * @return
	 * @describe 删除标签 同时清除用户标签
	 */
	@RequestMapping(value="_deleteLable")
	@ResponseBody
	@AdminTokenValidate
	public DataWrapper<Void> deleteLable(
			@RequestParam(value="adminToken",required=false) String adminToken,
			@RequestParam(value="lableId",required=true) int lableId
			){
		
		return lableManageService.deleteLable(adminToken,lableId);
	}
	/**
	 * @param 
	 * @return
	 * @describe 查询标签，非后台接口
	 */
	@RequestMapping(value="_querLables")
	@ResponseBody
	public DataWrapper<List<HashMap<String, Object>>> querLables(
			){
		return lableManageService.querLables();
	}
}
