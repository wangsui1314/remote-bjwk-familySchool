package com.bjwk.utils.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjwk.utils.CallStatusEnum;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.ErrorCodeEnum;



/**
 * aop捕获异常处理
 * @author liqitian
 * @version 1.0
 * @describe 
 * 2018年3月1日 上午1:31:55
 */

@ControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	@ResponseBody
    public DataWrapper<String> exceptionProcess(Exception ex) {
		
		ex.printStackTrace();
		
		DataWrapper<String> dataWrapper = new DataWrapper<String>();
		
		dataWrapper.setCallStatus(CallStatusEnum.FAILED);
		//System.out.println("错了");
		dataWrapper.setData(ex.getMessage());
		
        return dataWrapper;
    }
}
