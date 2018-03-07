package com.bjwk.service.impl.parent.nearman;

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
    	 if(redisTemplate==null){
    		 System.out.println("nullleleele");
    	 }
    	 ValueOperations<String, String> value = redisTemplate.opsForValue();
         value.set("lp", "hello word");
         System.out.println(value.get("lp"));
         
    	System.err.println(redisTemplate+ "  1232321");
    	ZSetOperations<String, String> zset=redisTemplate.opsForZSet();
    	System.out.println(zset);
        System.out.println(key+" 123" +beginScore+"   123");
    	zset.removeRangeByScore(key, 1, beginScore - 1);
        Set<String> ll=redisTemplate.opsForZSet().rangeByScore(key, beginScore, endScore);
        System.out.println(ll+"       66666");
        return ll;
    }

    @Override
    public void addOneStringToZSet(String key, String newVal, String oldVal, Integer score) {
        if (oldVal != null)
            redisTemplate.opsForZSet().remove(key, oldVal);
        redisTemplate.opsForZSet().add(key, newVal, score);
    }
}
