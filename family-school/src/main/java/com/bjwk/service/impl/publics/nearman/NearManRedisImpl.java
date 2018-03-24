package com.bjwk.service.impl.publics.nearman;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import com.bjwk.service.parent.nearman.NearManRedisService;

@Service
public class NearManRedisImpl implements NearManRedisService {

	 @Autowired

    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String getOneStringByKey(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void setOneStringByKey(String key, String value, int timeoutSeconds) {
        redisTemplate.opsForValue().set(key, value, timeoutSeconds, TimeUnit.SECONDS);
    }

    @Override
    public Set<String> getSetByKeyAndScore(String key, int beginScore, int endScore) {
         
    	ZSetOperations<String, String> zset=redisTemplate.opsForZSet();
    	zset.removeRangeByScore(key, 1, beginScore - 1);
        Set<String> ll=redisTemplate.opsForZSet().rangeByScore(key, beginScore, endScore);
        return ll;
    }

    @Override
    public void addOneStringToZSet(String key, String newVal, String oldVal, Integer score) {
        if (oldVal != null)
            redisTemplate.opsForZSet().remove(key, oldVal);
        redisTemplate.opsForZSet().add(key, newVal, score);
    }
}
