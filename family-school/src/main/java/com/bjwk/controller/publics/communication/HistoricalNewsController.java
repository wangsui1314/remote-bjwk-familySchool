package com.bjwk.controller.publics.communication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjwk.utils.DataWrapper;
import com.bjwk.zrongcloud.io.RongCloudKeyAndSecret;
import com.bjwk.zrongcloud.io.rong.RongCloud;
import com.bjwk.zrongcloud.io.rong.methods.message._private.Private;
import com.bjwk.zrongcloud.io.rong.methods.message.chatroom.Chatroom;
import com.bjwk.zrongcloud.io.rong.methods.message.discussion.Discussion;
import com.bjwk.zrongcloud.io.rong.methods.message.group.Group;
import com.bjwk.zrongcloud.io.rong.methods.message.history.History;
import com.bjwk.zrongcloud.io.rong.methods.message.system.MsgSystem;
import com.bjwk.zrongcloud.io.rong.models.response.HistoryMessageResult;
/**
 * 
 * @ClassName: HistoricalNewsController 
 * @Description: TODO("免费获取 APP 内指定某天某小时内的所有会话消息记录的下载地址") 
 * @author: lihui 
 * @date: 2018年3月31日 上午1:53:21
 */
@Controller
@RequestMapping("api/commin/histoy")
public class HistoricalNewsController {

	
	@RequestMapping("downHistoy")
	@ResponseBody
	public DataWrapper<HistoryMessageResult> downHistoy(
			//指定北京时间某天某小时，格式为2014010101，表示获取 2014 年 1 月 1 日凌晨 1 点至 2 点的数据。（必传）
			@RequestParam(value="date")  String date
			){

		/**
		 *
		 * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/message/history.html#get
		 *
		 * 获取历史消息日志文件
		 *
		 * */
		RongCloud rongCloud = RongCloud.getInstance(RongCloudKeyAndSecret.key, RongCloudKeyAndSecret.secret);
		//自定义 api 地址方式
		History history = rongCloud.message.history;
		HistoryMessageResult historyMessageResult = null;
		try {
			historyMessageResult = history.get(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataWrapper<HistoryMessageResult> dataWrapper=new DataWrapper<HistoryMessageResult>();
		dataWrapper.setData(historyMessageResult);
		System.out.println("get history  message:  " + historyMessageResult.toString());
		return dataWrapper;
	}
}
