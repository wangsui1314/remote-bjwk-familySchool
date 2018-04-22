package com.bjwk.service.impl.publics.nearman;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bjwk.controller.publics.nearman.ne.GeoCoordinate;
import com.bjwk.dao.NearManDao;
import com.bjwk.model.NearMan;
import com.bjwk.model.NearUsers;
import com.bjwk.model.pojo.Users;
import com.bjwk.service.parent.nearman.NearManRedisService;
import com.bjwk.service.parent.nearman.NearManService;
import com.bjwk.utils.CallStatusEnum;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.RedisClient;

import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.geo.GeoRadiusParam;
@Service
public class NearManImpl implements NearManService {



	@Autowired
	private NearManDao nearManDao;


	/**
	 * 附近的人实现：
	 * 1检查附近的人里是否有该用户的存在
	 *  如果有返回附近的人
	 *  如果没有将该用户信息插入，返回附近的人
	 */
	@Override
	public DataWrapper<List<NearUsers>> dearMan(NearMan nearMan) {
		// TODO Auto-generated method stub
		System.out.println(nearMan);
		/**
		 * 这里先取userId方便测试
		 */
		//存放user的集合
		List<String> nearUserList=new ArrayList<String>();
		String userId=nearMan.getUserId();
		DataWrapper<List<NearUsers>>  dataWrapper=new DataWrapper<List<NearUsers>>();
		Jedis jedis= RedisClient.getJedis();
		/**
		 * 检查附近的人里是否有该用户的存在
		 * 由于Redis没有提供判断ZSet中某个键是否存在指定成员的函数
		 * 这里用zrank返回有序集key中成员member的排名。其中有序集成员按score值递增(从小到大)顺序排列。检测用户
		 */

		try {
			Long ranking=jedis.zrank("nearMan", userId);

			//new map 为数据插入Distance
			Map<String,Object> distanceMap=new HashMap<String,Object>();
			/**
			 * map化赛选条件
			 *  "sex":"1",
			 *  "other":"1,3,2,4,5,6,7"
			 */
			Map<String,String> map = (Map<String,String>)JSON.parse(nearMan.getScreen());
			if(map==null) {
				map=new HashMap<String,String>();
			}
			//
			String str=map.get("other");
			if(str==null) {
				str=",";
			}
			System.out.println("rankingrankingranking   "+ranking);
			if(ranking==null) {
				//不存在
				if(addReo(nearMan.getLongitude(),nearMan.getLatitude(),nearMan.getUserId())==null) {
					dataWrapper.setMsg("失败");
					return dataWrapper;
				}else {

					List<GeoRadiusResponse> gLsit= geoQuery(nearMan.getLongitude(),nearMan.getLatitude());
					for(GeoRadiusResponse geo:gLsit){  
						//排除自己
						if(!geo.getMemberByString().equals(nearMan.getUserId())) {
							nearUserList.add(geo.getMemberByString());

							distanceMap.put(geo.getMemberByString(), geo.getDistance());
						}
						System.out.println(geo.getMemberByString()); //主键
						System.out.println(geo.getDistance());  //距离多少米  
					}

					if(nearUserList.isEmpty()) {
						dataWrapper.setMsg("没有附近的人");
						return dataWrapper;
					}
					List<NearUsers> list=nearManDao.queryNearMan(nearUserList, map.get("sex"),str.split(",")  );//附近的人   对象集合

					System.out.println(list);
					insertDistance(list,distanceMap);
					dataWrapper.setData(list);
					return dataWrapper;
				}
			}else {
				//存在
				List<GeoRadiusResponse> gLsit= geoQuery(nearMan.getLongitude(),nearMan.getLatitude());
				for(GeoRadiusResponse geo:gLsit){  
					//排除自己
					if(!geo.getMemberByString().equals(nearMan.getUserId())) {
						nearUserList.add(geo.getMemberByString());
						distanceMap.put(geo.getMemberByString(), geo.getDistance());
					}
					System.out.println(geo.getMemberByString()); //主键
					System.out.println(geo.getDistance());  //距离多少米  
				}
				/**
				 * 如果附近的人为empty 则直接返回
				 */
				if(nearUserList.isEmpty()) {
					dataWrapper.setMsg("没有附近的人");
					return dataWrapper;
				}
				List<NearUsers> list=nearManDao.queryNearMan(nearUserList, map.get("sex"),str.split(",")  );//附近的人   对象集合
				
				System.out.println(distanceMap);
				insertDistance(list,distanceMap);
				dataWrapper.setData(list);
				return dataWrapper;


			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			jedis.close();
		}
		return null;
	}
	private List<NearUsers>  insertDistance(List<NearUsers> mapList,Map<String,Object> map) {
         for(String userId:map.keySet()) {
//        	 for(NearUsers nearUser:mapList) {
//        		 if(userId.equals(nearUser.getUserId())) {
//        			 System.out.println(userId);
//        			 nearUser.setDistance((Double) map.get("userId"));
//        		 }
//        	 }
        	 for(int i=0;i<mapList.size();i++) {
        		 if(userId.equals(mapList.get(i).getUserId())) {
        			 System.out.println(userId+"   userIduserIduserIduserIduserId");
        			 System.out.println((Double)map.get("userId"));
        			 mapList.get(i).setDistance((Double)map.get(userId));
        			 System.out.println(mapList+"   mapList");
        		 }
        	 }
         }
         return mapList;
	}

	/**
	 * 添加坐标
	 * key 经度  维度  距离
	 * return m 表示单位为米*/
	public static Long addReo(double lng, double lat,String userId) {
		Jedis jedis= RedisClient.getJedis();
		try {
			//第一个参数可以理解为表名
			return jedis.geoadd("nearMan",lng, lat,userId);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if (null != jedis)
				jedis.close();
		}
		return null;
	}


	/**
	 * 查询附近人
	 * key 经度  维度  距离
	 * return GeoRadiusResponse*/
	public static List<GeoRadiusResponse> geoQuery(double lng, double lat) {
		Jedis jedis= RedisClient.getJedis();
		try {
			// jedis.zrem(key, members)
			//200F GeoUnit.KM表示km 
			return jedis.georadius("nearMan",lng,lat,200L,GeoUnit.KM, 
					GeoRadiusParam.geoRadiusParam().withDist().sortAscending()
					);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if (null != jedis)
				jedis.close();
		}
		return null;
	}
}

