package com.bjwk.service.impl.parent.nearman;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bjwk.model.NearMan;
import com.bjwk.service.parent.nearman.NearManRedisService;
import com.bjwk.service.parent.nearman.NearManService;
import com.bjwk.utils.CallStatusEnum;
import com.bjwk.utils.DataWrapper;
@Service
public class NearManImpl implements NearManService {
    
	/** 2017-09-01 毫秒值/1000 (秒) **/
    private static final int BASE_SORT_NUM = 1504195200;

    /** 最大距离 **/
    private static final int MAX_DISTANCE = 3000;

    /** 8小时（秒） **/
     static final int EIGHT_HOUR_SECOND = 60 * 60 * 8;

    /** 附近的人缓存key值，p1-城市编号，p2-地区编号 **/
    private static final String NEARBY_CACHE_KEY = "nearby_%s_%s";

    /** 附近的人用户缓存key值，p1-城市编号，p2-地区编号,p3-用户id **/
     static final String NEARBY_USER_CACHE_KEY = "nearby_user_%s_%s_%s";

    @Autowired
    private NearManRedisService nearManRedisService;

    // 线程池
    @Autowired
    private ThreadPoolTaskExecutor threadPool;

    
	/**
	 * 附近的人
	 */
	@Override
	public DataWrapper<List<NearMan>> dearMan(NearMan nearMan) {
		// TODO Auto-generated method stub
		  DataWrapper<List<NearMan>>  dataWrapper=new DataWrapper<List<NearMan>>();
		    int nowSortNum = (int) (new Date().getTime() / 1000);
	        // 此处仅为了减低排序的序号（ 获取缓存集合最大排序下标）
	        int endIndex = nowSortNum - BASE_SORT_NUM;

	        // 缓存key值
	        String cacheKey = String.format(NEARBY_CACHE_KEY, nearMan.getCityCode(), nearMan.getAdCode());

	        // 取同一城市地区&&八小时区间范围数据(八小时之前缓存数据会删除)
	        Set<String> redisNearby = nearManRedisService.getSetByKeyAndScore(cacheKey, endIndex - EIGHT_HOUR_SECOND, endIndex);
	        // 开启新线程写入数据(让主线程处理主业务)
	        threadPool.execute(new InsertCache(nearMan, cacheKey, endIndex));

	        if (redisNearby.size() == 0){
	        	dataWrapper.setCallStatus(CallStatusEnum.FAILED);
	        	dataWrapper.setMsg("附近查无用户");
	        }

	        List<NearMan> result = new ArrayList<NearMan>(redisNearby.size());
	        boolean oneself = true;
	        for (String item : redisNearby) {
	        	NearMan cacheNearby = JSONObject.parseObject(item, NearMan.class);
	            // 缓存里可能有用户自己
	            if (cacheNearby.getUserId().intValue() == nearMan.getUserId())
	                continue;
	            //计算两经纬度点之间的距离
	            double distance = countDistance(nearMan.getLongitude(), nearMan.getLatitude(), cacheNearby.getLongitude(),
	                    cacheNearby.getLatitude());
	            // 大于限定距离
	            if (distance > MAX_DISTANCE)
	                continue;
	            
	            result.add(new NearMan(cacheNearby.getUserId(), cacheNearby.getName(), distance));
	            oneself = false;
	        }
	        if (oneself){
	        	dataWrapper.setCallStatus(CallStatusEnum.FAILED);
	        	dataWrapper.setMsg("附近查无用户");
	        	return dataWrapper;
	        }
	        dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
	        dataWrapper.setMsg("获取成功");
	        dataWrapper.setData(result);
	        return dataWrapper;	}
	/**
     * 计算两经纬度点之间的距离（单位：米）
     * 
     * @param longitude1
     *            坐标1经度
     * @param latitude1
     *            坐标1纬度
     * @param longitude2
     *            坐标2经度
     * @param latitude2
     *            坐标1纬度
     * @return
     */
    private static double countDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        double radLat1 = Math.toRadians(latitude1);
        double radLat2 = Math.toRadians(latitude2);
        double a = radLat1 - radLat2;
        double b = Math.toRadians(longitude1) - Math.toRadians(longitude2);
        double s = 2 * Math.asin(Math.sqrt(
                Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6378137.0;
        s = Math.round(s * 10000) / 10000;
        return s;
    }
}


