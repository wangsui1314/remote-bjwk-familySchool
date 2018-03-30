package com.bjwk.service.publics.communication;

import com.bjwk.utils.DataWrapper;

public interface MessageSentService {

	DataWrapper<Object> singleTxtMsgChat(String token, String[] targetIds, String objectName, String content);

}
