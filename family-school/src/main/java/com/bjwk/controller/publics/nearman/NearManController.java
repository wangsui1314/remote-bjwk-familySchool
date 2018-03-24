package com.bjwk.controller.publics.nearman;

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
	
	@RequestMapping("getNearList")
	@ResponseBody
	public DataWrapper<List<NearMan>> dearMan(@Valid @ModelAttribute NearMan dearMan){
		_logger.info("获取附近的人....");
		return nearManService.dearMan(dearMan);
	}
}
