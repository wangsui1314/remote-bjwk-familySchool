package com.bjwk.utils;

public class DataWrapper<T>  {
    private CallStatusEnum callStatus;
    private ErrorCodeEnum errorCode;
    private T data;
    private String token;

    // 用于分页结果
    private int numberPerPage;
    private int currentPage;
    private int totalNumber;
    private int totalPage;
    private int num;
    private String msg;
    private String fl;

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

    public String getFl() {
		return fl;
	}

	public void setFl(String fl) {
		this.fl = fl;
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

    public int getNumberPerPage() {
        return numberPerPage;
    }

    public void setNumberPerPage(int numberPerPage) {
        this.numberPerPage = numberPerPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    
    

    public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setPage(Page page, int totalNumber){
        this.totalNumber = totalNumber;
        this.currentPage = page.getCurrentPage();
        this.numberPerPage = page.getNumberPerPage();
        if(numberPerPage != 0){
            this.totalPage = (int)Math.ceil((double) totalNumber/(double) numberPerPage);
        }else {
            this.totalPage = 1;
        }
    }

	@Override
	public String toString() {
		return "DataWrapper [callStatus=" + callStatus + ", errorCode="
				+ errorCode + ", data=" + data + ", token=" + token
				+ ", numberPerPage=" + numberPerPage + ", currentPage="
				+ currentPage + ", totalNumber=" + totalNumber + ", totalPage="
				+ totalPage + ", num=" + num + ", msg=" + msg + "]";
	}

	public DataWrapper(ErrorCodeEnum errorCodeEnum,T data,String token,Integer num,String msg,Page page,Integer totalNumber){
	    this.setErrorCode(errorCodeEnum);
	    this.data=data;
	    this.token=token;
	    this.num=num;
	    this.setPage(page,totalNumber);
	    this.msg=msg;
    }
   
}
