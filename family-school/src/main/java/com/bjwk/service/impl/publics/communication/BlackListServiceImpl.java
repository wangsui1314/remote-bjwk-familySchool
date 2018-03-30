package com.bjwk.service.impl.publics.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjwk.dao.RegLoginDao;
import com.bjwk.service.publics.communication.BlackListService;
import com.bjwk.utils.CallStatusEnum;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.RedisClient;
import com.bjwk.zrongcloud.io.RongCloudKeyAndSecret;
import com.bjwk.zrongcloud.io.rong.RongCloud;
import com.bjwk.zrongcloud.io.rong.methods.user.blacklist.Blacklist;
import com.bjwk.zrongcloud.io.rong.models.Result;
import com.bjwk.zrongcloud.io.rong.models.response.BlackListResult;
import com.bjwk.zrongcloud.io.rong.models.user.UserModel;

import redis.clients.jedis.Jedis;

@Service
public class BlackListServiceImpl implements BlackListService{

	@Autowired
	private RegLoginDao regLoginDao;

	//加入黑名单
	@Override
	public DataWrapper<Void> addBlack(String token, String blackUserId) {
		// TODO Auto-generated method stub
		DataWrapper<Void>  dataWrapper=new DataWrapper<Void>();
		Jedis  jedis=RedisClient.getInstance().getJedis();
		String userName=jedis.hget("loginStatus", token);
		if(userName==null){
			dataWrapper.setMsg("令牌错误");
			dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			return dataWrapper;
		}
		String userId=regLoginDao.getUserIdByUserName(userName);

		Result res=addOrRemoveBlackMethods(userId,blackUserId,1);
		if(res.getCode()==200) {
			dataWrapper.setMsg("拉黑成功");
			dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
			return dataWrapper;
		}
		dataWrapper.setCallStatus(CallStatusEnum.FAILED);
		return dataWrapper;
	}

	//移出黑名单
	@Override
	public DataWrapper<Void> removeBlack(String token, String blackUserId) {
		// TODO Auto-generated method stub
		DataWrapper<Void>  dataWrapper=new DataWrapper<Void>();
		Jedis  jedis=RedisClient.getInstance().getJedis();
		String userName=jedis.hget("loginStatus", token);
		if(userName==null){
			dataWrapper.setMsg("令牌错误");
			dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			return dataWrapper;
		}
		String userId=regLoginDao.getUserIdByUserName(userName);

		Result res=addOrRemoveBlackMethods(userId,blackUserId,2);
		if(res.getCode()==200) {
			dataWrapper.setMsg("移除黑名单成功");
			dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
			return dataWrapper;
		}
		dataWrapper.setCallStatus(CallStatusEnum.FAILED);
		return dataWrapper;
	}
	//查看黑名单
	@Override
	public DataWrapper<BlackListResult> queryBlack(String token) {
		// TODO Auto-generated method stub

		DataWrapper<BlackListResult>  dataWrapper=new DataWrapper<BlackListResult>();
		Jedis  jedis=RedisClient.getInstance().getJedis();
		String userName=jedis.hget("loginStatus", token);
		if(userName==null){
			dataWrapper.setMsg("令牌错误");
			dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			return dataWrapper;
		}
		String userId=regLoginDao.getUserIdByUserName(userName);

		try {
			RongCloud rongCloud = RongCloud.getInstance(RongCloudKeyAndSecret.key, RongCloudKeyAndSecret.secret);

			Blacklist BlackList = rongCloud.user.blackList;
			UserModel user = new UserModel().setId(userId);
			BlackListResult result = BlackList.getList(user);
			dataWrapper.setData(result);
			return dataWrapper;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @Title: addOrRemoveBlackMethods 
	 * @Description: TODO(添加或者移除用户（黑名单中）) 
	 * @param @param userId
	 * @param @param blackUserId
	 * @param @param type
	 * @param @return    设定文件 
	 * @return Result    返回类型 
	 * @throws
	 */
	private  Result addOrRemoveBlackMethods(String userId,String blackUserId,int type ){

		RongCloud rongCloud = RongCloud.getInstance(RongCloudKeyAndSecret.key, RongCloudKeyAndSecret.secret);
		Blacklist BlackList = rongCloud.user.blackList;


		UserModel blackUser = new UserModel().setId(blackUserId);
		UserModel[] blacklist = {blackUser};
		UserModel user = new UserModel()
				.setId(userId)
				.setBlacklist(blacklist);
		Result res=null;
		try {
			if(type==1) {
				/*
				 * 添加用户到黑名单
				 */
				res = (Result)BlackList.add(user);
			}else if(type==2) {
				/**
				 * 从黑名单移除
				 */
				res  = BlackList.remove(user);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}

}
