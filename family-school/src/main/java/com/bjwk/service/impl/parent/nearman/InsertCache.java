package com.bjwk.service.impl.parent.nearman;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.bjwk.model.NearMan;
import com.bjwk.service.parent.nearman.NearManRedisService;

public class InsertCache implements Runnable {
    // 用户提交的最新坐标信息
    private NearMan paramObj;
    // “附近的人”缓存集合key
    private String cacheKey;
    // 获取缓存集合最大排序下标
    private Integer endIndex;
    
    @Autowired
    private NearManRedisService nearManRedisService;
    public InsertCache(NearMan paramObj, String cacheKey, Integer endIndex) {
        this.paramObj = paramObj;
        this.cacheKey = cacheKey;
        this.endIndex = endIndex;
    }

    @Override
    public void run() {
        String userCacheKey = String.format(NearManImpl.NEARBY_USER_CACHE_KEY, paramObj.getCityCode(), paramObj.getAdCode(),
                paramObj.getUserId());
        String cacheNewData = JSONObject.toJSONString(paramObj);
        String cacheUserPosition = nearManRedisService.getOneStringByKey(userCacheKey);
        // 确保用户坐标信息缓存清除慢于“附近的人”坐标信息
        nearManRedisService.setOneStringByKey(userCacheKey, cacheNewData, NearManImpl.EIGHT_HOUR_SECOND + 60);

        // 保存用户坐标信息至“附近的人”缓存集合
        nearManRedisService.addOneStringToZSet(cacheKey, cacheNewData, cacheUserPosition, endIndex);
    }

}
