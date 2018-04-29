package com.bjwk.service.impl.admin;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjwk.dao.LableManageDao;
import com.bjwk.service.admin.LableManageService;
import com.bjwk.utils.CallStatusEnum;
import com.bjwk.utils.DataWrapper;

@Service
public class LableManageServiceImpl implements LableManageService {

	@Autowired
	private LableManageDao lableManaegDao;
	@Override
	public DataWrapper<Void> insertLable(String adminToken, String labeName,String lableType) {
		// TODO Auto-generated method stub
		/**
		 * 切面验证管理员token
		 */
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		if(lableManaegDao.insertLable(labeName,lableType)==0) {
			dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			return dataWrapper;
		}
		return dataWrapper;
	}
	@Override
	public DataWrapper<Void> deleteLable(String adminToken, int lableId) {
		// TODO Auto-generated method stub
		
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		if(lableManaegDao.deleteLable(lableId)==0) {
			
			dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			return dataWrapper;
		}
		lableManaegDao.deleteLableToUserLable(lableId);
		return dataWrapper;
	}
	/**
	 * 查询所有标签（非后台接口）
	 */
	@Override
	public DataWrapper<List<HashMap<String, Object>>> querLables() {
		// TODO Auto-generated method stub
		DataWrapper<List<HashMap<String, Object>>> dataWrapper=new DataWrapper<List<HashMap<String, Object>>>();
		dataWrapper.setData(lableManaegDao.querLables());
		return dataWrapper;
	}

}
