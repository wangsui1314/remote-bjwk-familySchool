package com.bjwk.service.impl.publics.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjwk.dao.RegLoginDao;
import com.bjwk.service.publics.communication.MessageSentService;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.UserNameUtil;
import com.bjwk.zrongcloud.io.RongCloudKeyAndSecret;
import com.bjwk.zrongcloud.io.rong.RongCloud;
import com.bjwk.zrongcloud.io.rong.messages.TxtMessage;
import com.bjwk.zrongcloud.io.rong.methods.message._private.Private;
import com.bjwk.zrongcloud.io.rong.models.message.PrivateMessage;
import com.bjwk.zrongcloud.io.rong.models.response.ResponseResult;

@Service
public class MessageSentServiceImpl implements MessageSentService {

	@Autowired
    private RegLoginDao regLoginDao;
	
	@Override
	public DataWrapper<Object> singleTxtMsgChat(String token, String[] targetIds, String objectName, String content) {
		// TODO Auto-generated method stub
		DataWrapper<Object>  dataWrapper=new DataWrapper<Object>();
		String userName=UserNameUtil.getUserNameBytoken(token);
		String userId=regLoginDao.getUserIdByUserName(userName);
		System.out.println(userName+"   "+userId);
		/**
		 * 发送单聊文字消息
		 */
		try {
			RongCloud rongCloud = RongCloud.getInstance(RongCloudKeyAndSecret.key, RongCloudKeyAndSecret.secret);
			Private Private = rongCloud.message.msgPrivate;
			TxtMessage txtMessage = new TxtMessage(content, "test");
			PrivateMessage privateMessage = new PrivateMessage()
					.setSenderUserId(userId)
					.setTargetId(targetIds)
					.setObjectName(txtMessage.getType())
					.setContent(txtMessage)
					.setPushContent("")
					.setPushData("{\"pushData\":\"hello\"}")
					.setCount("4")
					.setVerifyBlacklist(1)
					.setIsPersisted(0)
					.setIsCounted(0)
					.setIsIncludeSender(0);
			ResponseResult privateResult = Private.send(privateMessage);
			dataWrapper.setData(privateResult);
			
			System.out.println("send private message:  " + privateResult.toString());
			return dataWrapper;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
