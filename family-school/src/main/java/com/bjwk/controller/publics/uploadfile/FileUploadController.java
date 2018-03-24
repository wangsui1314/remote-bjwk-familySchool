package com.bjwk.controller.publics.uploadfile;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.ErrorCodeEnum;
import com.bjwk.utils.UploadFile;


@Controller
@RequestMapping("api/file")
public class FileUploadController {
	/**
	 * 为客户端颁发上传凭证
	 * @return
	 */
	@RequestMapping("getUpToken")
	@ResponseBody
	public DataWrapper<String> getUpToken(){
		DataWrapper<String> dataWrapper =new DataWrapper<String>();
		String upToken =UploadFile.getUpToken();
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		dataWrapper.setData(upToken);
		return dataWrapper;
	}
	
}
