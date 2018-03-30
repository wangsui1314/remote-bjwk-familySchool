package com.bjwk.service.impl.publics.communication;

import com.bjwk.service.publics.communication.SensitiveWordService;
import com.bjwk.utils.CallStatusEnum;
import com.bjwk.utils.DataWrapper;
import com.bjwk.zrongcloud.io.RongCloudKeyAndSecret;
import com.bjwk.zrongcloud.io.rong.RongCloud;
import com.bjwk.zrongcloud.io.rong.methods.sensitive.SensitiveWord;
import com.bjwk.zrongcloud.io.rong.models.response.ListWordfilterResult;
import com.bjwk.zrongcloud.io.rong.models.response.ResponseResult;
import com.bjwk.zrongcloud.io.rong.models.sensitiveword.SensitiveWordModel;

public class SensitiveWordServiceImpl implements SensitiveWordService {

	@Override
	public DataWrapper<Void> addSensWord(String adminToken, String word, String replaceWord) {
		// TODO Auto-generated method stub
		/**
		 * 校验管理员 权限
		 */
		//some code
		 RongCloud rongCloud = RongCloud.getInstance(RongCloudKeyAndSecret.key, RongCloudKeyAndSecret.secret);
	        //自定义 api 地址方式
	        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);
            DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
	        SensitiveWord SensitiveWord = rongCloud.sensitiveword;

	        /**
	         *API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/sensitive/sensitive.html#add
	         *
	         * 添加替换敏感词方法
	         *
	         * */
	        SensitiveWordModel sentiveWord = new SensitiveWordModel()
	                .setType(0)
	                .setKeyWord(word)
	                .setReplace(replaceWord);
	        ResponseResult addesult = null;
			try {
				addesult = SensitiveWord.add(sentiveWord);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(addesult.getCode()!=200) {
				dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			}
	        System.out.println("sentiveWord add:  " + addesult.toString());
	        return dataWrapper;
	}

	@Override
	public DataWrapper<Void> removeSensWord(String adminToken, String word) {
		// TODO Auto-generated method stub
		 RongCloud rongCloud = RongCloud.getInstance(RongCloudKeyAndSecret.key, RongCloudKeyAndSecret.secret);
	        //自定义 api 地址方式
	        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);
         DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
	        SensitiveWord SensitiveWord = rongCloud.sensitiveword;
	        ResponseResult removeesult = null;
			try {
				removeesult = SensitiveWord.remove(word);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       if(removeesult.getCode()!=200) {
	    	   dataWrapper.setCallStatus(CallStatusEnum.FAILED);
	       }
		return dataWrapper;
	}

	@Override
	public DataWrapper<ListWordfilterResult> querySensWordList(String adminToken, String type) {
		// TODO Auto-generated method stub
		  /**
		   * 校验管理员权限
		   * some code
		   */
		 RongCloud rongCloud = RongCloud.getInstance(RongCloudKeyAndSecret.key, RongCloudKeyAndSecret.secret);
	        //自定义 api 地址方式
	        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);
		 DataWrapper<ListWordfilterResult> dataWrapper=new DataWrapper<ListWordfilterResult>();
	        SensitiveWord SensitiveWord = rongCloud.sensitiveword;
	        ListWordfilterResult result = null;
			try {
				result = SensitiveWord.getList(Integer.parseInt(type));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(result.getCode()!=200) {
		    	   dataWrapper.setCallStatus(CallStatusEnum.FAILED);
		       }
	        System.out.println("getList:  " + result.toString());
	        return dataWrapper;
	}

}
