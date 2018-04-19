package com.bjwk.utils.sms;

import com.bjwk.controller.publics.VerifyCodeU;
import com.bjwk.utils.ErrorCodeEnum;

/**
 * 
 * @ClassName: VerifiCodeValidateUtil 
 * @Description: TODO("") 
 * @author: lihui 
 * @date: 2018年4月19日 下午11:45:17
 */
public class VerifiCodeValidateUtil {
    public static ErrorCodeEnum verifiCodeValidate(String phone, String code){
        String serverCode = new VerifyCodeU().getPhoneCode(phone);
        if ("noCode".equals(serverCode)) {
            return ErrorCodeEnum.Verify_Code_notExist;
        } else if ("overdue".equals(serverCode)) {
            return ErrorCodeEnum.Verify_Code_5min;
        } else if (serverCode.equals(code)) {
            return ErrorCodeEnum.No_Error;
        }else {
           return ErrorCodeEnum.Verify_Code_Error;
        }
    }
}
