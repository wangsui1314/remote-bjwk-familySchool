package com.bjwk.utils;

import com.github.pagehelper.PageInfo;
import lombok.Data;


@Data
public class DataWrapper<T>  {
    private CallStatusEnum callStatus;
    private ErrorCodeEnum errorCode;
    private T data;
    private String token;


    private String msg;
    private String rongCloudToken;

    public DataWrapper() {
        callStatus = CallStatusEnum.SUCCEED;
        errorCode = ErrorCodeEnum.No_Error;
    }

    public CallStatusEnum getCallStatus() {
        return callStatus;
    }

    public void setCallStatus(CallStatusEnum callStatus) {
        this.callStatus = callStatus;
    }

    public ErrorCodeEnum getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCodeEnum errorCode) {
        this.errorCode = errorCode;
       if (!errorCode.equals(ErrorCodeEnum.No_Error)) {
         this.callStatus = CallStatusEnum.FAILED;
       }
       this.msg=errorCode.getLabel();

    }

    public T getData() {
        return data;
    }

   

	public void setData(T data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRongCloudToken() {
		return rongCloudToken;
	}

	public void setRongCloudToken(String rongCloudToken) {
		this.rongCloudToken = rongCloudToken;
	}

	public DataWrapper(ErrorCodeEnum errorCodeEnum,T data,String msg){
	    this.setErrorCode(errorCodeEnum);
	    this.data=data;
	   // this.setPage(page,totalNumber);
	    this.msg=msg;
    }

}
