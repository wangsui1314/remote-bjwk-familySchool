package com.bjwk.service.publics.communication;

import com.bjwk.utils.DataWrapper;
import com.bjwk.zrongcloud.io.rong.models.response.ListWordfilterResult;

public interface SensitiveWordService {

	DataWrapper<Void> addSensWord(String adminToken, String word, String replaceWord);

	DataWrapper<Void> removeSensWord(String adminToken, String word);

	DataWrapper<ListWordfilterResult> querySensWordList(String adminToken, String type);

}
