package com.bjwk.utils.sms;

import com.bjwk.utils.ErrorCodeEnum;

/**
 * Created by 小月亮 on 2017/9/12.
 */
public class VerifiCodeValidateUtil {
    public static ErrorCodeEnum verifiCodeValidate(String phone, String code){
        String serverCode = VerifyCodeManager.getPhoneCode(phone);
        if ("noCode".equals(serverCode)) {
            return ErrorCodeEnum.Verify_Code_notExist;
        } else if ("overdue".equals(serverCode)) {
            return ErrorCodeEnum.Verify_Code_10min;
        } else if (serverCode.equals(code)) {
            return ErrorCodeEnum.No_Error;
        }else {
           return ErrorCodeEnum.Verify_Code_Error;
        }
    }
}
