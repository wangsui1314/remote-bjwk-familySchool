package com.bjwk.service.impl.publics.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjwk.dao.RegLoginDao;
import com.bjwk.service.publics.communication.GroupService;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.UserNameUtil;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private RegLoginDao regLoginDao;
	@Override
	public DataWrapper<Void> cerateGroup(String token, String groupName, String type) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		String userName=UserNameUtil.getUserNameBytoken(token);
		String userId=regLoginDao.getUserIdByUserName(userName);
		/**
		 * 生成群组id的策略
		 */
		return null;
	}
	
    private String createdGroupStrate(String userId) {
    	String groupId=userId+"+"+((Math.random()*9+1)*100000);
		return groupId;
    }
}
