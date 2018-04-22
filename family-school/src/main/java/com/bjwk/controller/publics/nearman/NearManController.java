package com.bjwk.controller.publics.nearman;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjwk.model.NearMan;
import com.bjwk.model.NearUsers;
import com.bjwk.service.parent.nearman.NearManService;
import com.bjwk.utils.DataWrapper;

/**
 * 
 * @author liqitian
 * @version 1.0
 * @describe  附近的人 
 * 2018年3月6日 下午10:08:57
 */
@Controller
@RequestMapping("api/nearman")
public class NearManController {
	
	private static final Log _logger = LogFactory.getLog(NearManController.class);
	
	@Autowired
	private NearManService nearManService;
	
	/**
	 * 测试说数据
	 * http://localhost:8080/family-school/api/nearman/getNearList?longitude=121.255904&latitude=31.351319&userId=2
	 * http://localhost:8080/family-school/api/nearman/getNearList?longitude=121.25166&latitude=31.3514&userId=1
	* @Title: dearMan 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param dearMan
	* @param @return    设定文件 
	* @return DataWrapper<List<NearUsers>>    返回类型 
	* @throws
	 */
	@RequestMapping("getNearList")
	@ResponseBody
	public  DataWrapper<List<NearUsers>> dearMan(@Valid @ModelAttribute NearMan dearMan){
		_logger.info("获取附近的人....");
		return nearManService.dearMan(dearMan);
	}
}
