package com.bjwk.controller.publics;



import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bjwk.utils.BeanUtil;
import com.bjwk.utils.RedisClient;
import com.bjwk.utils.TimeUtil;

import redis.clients.jedis.Jedis;


/**
 * 
 * @author liqitian
 * @version 1.0
 * @describe 
 * 2018年3月22日 下午11:26:23
 */
@Component
public class VerifyCodeU {
    private static int minute = 5;
    //private static HashMap<String, String> USER_CODE_MAP = new HashMap<String, String>();
  
//    @Autowired
//    
//    private  RedisTemplate<String, String> redisTemplate;

    
    public  String newPhoneCode(String phoneNum) {
    	Jedis jedis=RedisClient.getJedis();
    	
        Random random = new Random();
        int a = random.nextInt(8999)+1000;
        String code = String.valueOf(a);
        String oldCode = getPhoneCodeNew(phoneNum);
        Date nowTime = new Date();
        if("overdue".equals(oldCode)||"noCode".equals(oldCode)){
        	System.out.println(code+ TimeUtil.changeDateToString(nowTime));
        	jedis.hset("USER_CODE_MAP",phoneNum,code+TimeUtil.changeDateToString(nowTime));
        	
           // USER_CODE_MAP.put(phoneNum,code+ TimeUtil.changeDateToString(nowTime));
            return code;
        } else{
            return null;
        }
    }

    public  String getPhoneCode(String phoneNum){
    	Jedis jedis=RedisClient.getJedis();
    	
        try {
            System.out.println(phoneNum+":  "+"contain:"+jedis.hexists("USER_CODE_MAP",phoneNum));
            
            if(jedis.hexists("USER_CODE_MAP",phoneNum)){
            	System.out.println( "    8888"   +jedis.hget("USER_CODE_MAP", phoneNum));
                if (TimeUtil.timeBetween(TimeUtil.changeStringToDate(((String) jedis.hget("USER_CODE_MAP", phoneNum)).substring(4)), new Date()) / (60 * 1000) > minute){

                    removePhoneCodeByPhoneNum(phoneNum);
                    return "overdue";
                }else{
                	String a= ((String) jedis.hget("USER_CODE_MAP", phoneNum)).substring(0,4);
                    return  a;
                }
            }
            else {
                return "noCode";
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**修改两次发送验证码的时间间隔为1分钟
     * @param phoneNum
     * @return
     */
    public  String getPhoneCodeNew(String phoneNum){
    	Jedis jedis=RedisClient.getJedis();

    	
        try {
        
           // System.out.println("contain:"+redisTemplate.opsForHash().hasKey("USER_CODE_MAP",phoneNum));
            if(jedis.hexists("USER_CODE_MAP",phoneNum)){
                if (checkTime(TimeUtil.changeStringToDate(((String) jedis.hget("USER_CODE_MAP", phoneNum)).substring(4)), new Date())> 50){
                    removePhoneCodeByPhoneNum(phoneNum);
                    return "overdue";
                }else{
                    String a=((String) jedis.hget("USER_CODE_MAP", phoneNum)).substring(0,4);
                    return  a;
                }
            }
            else {
                return "noCode";
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**发送验证码时时间的判断
     */
    public static int checkTime(Date startDate,Date endDate){
        try{
            int time=(int)(endDate.getTime()-startDate.getTime());
            int checkTime=time/5000;//时间间隔的秒数
            return checkTime;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }



    /**
     * 删除某用户的验证码Code
     */
    public  void removePhoneCodeByPhoneNum(String phoneNum) {
    	Jedis jedis=RedisClient.getJedis();
        if (jedis.hexists("USER_CODE_MAP",phoneNum)) {
        	
        	jedis.hdel("USER_CODE_MAP","phoneNum");
        }
    }

}
