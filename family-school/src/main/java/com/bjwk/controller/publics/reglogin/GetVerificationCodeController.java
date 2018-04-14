package com.bjwk.controller.publics.reglogin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjwk.model.pojo.Users;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.ErrorCodeEnum;
import com.bjwk.utils.sms.SMSUtils;
import com.bjwk.utils.sms.VerifyCodeManager;

/**
 * 
 * @author liqitian
 * @version 1.0
 * @describe 
 * 2018年3月23日 上午12:15:00
 */
@Controller
@RequestMapping("api/get")
public class GetVerificationCodeController {

	private static final Log _logger = LogFactory.getLog(GetVerificationCodeController.class);

	@RequestMapping(value="_getVcode")
	@ResponseBody
	public DataWrapper<Void> getVcode(
			@RequestParam(value="phone",required=true) String phone
			){
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		_logger.info(phone+"：用户开始获取验证码....");
		
		String code = VerifyCodeManager.newPhoneCode(phone);
		if (code == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_10min);
			dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
			return dataWrapper;
		}
		boolean result=SMSUtils.sendPhoneVerifyCode(phone, code);
		if (!result) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
		}
		return dataWrapper;
	}
}
