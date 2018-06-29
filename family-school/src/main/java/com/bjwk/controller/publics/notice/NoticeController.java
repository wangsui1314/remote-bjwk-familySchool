package com.bjwk.controller.publics.notice;

import com.bjwk.model.notice.NoticeEntity;
import com.bjwk.service.publics.notice.NoticeService;
import com.bjwk.utils.CallStatusEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjwk.utils.DataWrapper;


/** 
* @Description: 公告Controller
* @author  Desolation
* @email:1071680460@qq.com
* @date 创建时间：2018年5月1日 上午12:21:11 
* @version 1.0  
*/
@Controller
@RequestMapping("api/public/notice")
public class NoticeController {
	
	private static final Log _logger = LogFactory.getLog(NoticeController.class);

	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("_addNotice")
	@ResponseBody
	public DataWrapper<String> addNotice(){
		_logger.info("添加公告");
		
		return null;
	}

	@RequestMapping("_noticeDetails")
	public DataWrapper<NoticeEntity> findNoticeDetails(int noticeId){
		_logger.info("获取公告详情"+noticeId);
		DataWrapper<NoticeEntity> dataWrapper = new DataWrapper<NoticeEntity>();
		NoticeEntity entity = noticeService.findNoticeDetails(noticeId);
		dataWrapper.setData(entity);
		dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
		dataWrapper.setMsg("查询公告详情正确");
		return dataWrapper;
	}
}
