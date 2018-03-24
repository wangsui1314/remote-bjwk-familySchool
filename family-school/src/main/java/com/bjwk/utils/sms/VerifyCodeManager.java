package com.bjwk.utils.sms;



import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import com.bjwk.utils.TimeUtil;


/**
 * 
 * @author liqitian
 * @version 1.0
 * @describe 
 * 2018年3月22日 下午11:26:23
 */
public class VerifyCodeManager {
    private static int minute = 10;
    private static HashMap<String, String> USER_CODE_MAP = new HashMap<String, String>();
    public static String newPhoneCode(String phoneNum) {
        Random random = new Random();
        int a = random.nextInt(8999)+1000;
        String code = String.valueOf(a);
        String oldCode = getPhoneCodeNew(phoneNum);
        Date nowTime = new Date();
        if("overdue".equals(oldCode)||"noCode".equals(oldCode)){
        	System.out.println(code+ TimeUtil.changeDateToString(nowTime));
            USER_CODE_MAP.put(phoneNum,code+ TimeUtil.changeDateToString(nowTime));
            return code;
        } else{
            return null;
        }
    }

    public static String getPhoneCode(String phoneNum){
        try {
            System.out.println(phoneNum+":  "+"contain:"+USER_CODE_MAP.containsKey(phoneNum));
            if(USER_CODE_MAP.containsKey(phoneNum)){
                if (TimeUtil.timeBetween(TimeUtil.changeStringToDate(USER_CODE_MAP.get(phoneNum).substring(4)), new Date()) / (60 * 1000) > minute){
                    VerifyCodeManager.removePhoneCodeByPhoneNum(phoneNum);
                    return "overdue";
                }else{
                	String a=USER_CODE_MAP.get(phoneNum).substring(0,4);
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
    public static String getPhoneCodeNew(String phoneNum){
        try {
            System.out.println("contain:"+USER_CODE_MAP.containsKey(phoneNum));
            if(USER_CODE_MAP.containsKey(phoneNum)){
                if (checkTime(TimeUtil.changeStringToDate(USER_CODE_MAP.get(phoneNum).substring(4)), new Date())> 50){
                    VerifyCodeManager.removePhoneCodeByPhoneNum(phoneNum);
                    return "overdue";
                }else{
                    String a=USER_CODE_MAP.get(phoneNum).substring(0,4);
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
            int checkTime=time/1000;//时间间隔的秒数
            return checkTime;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }



    /**
     * 删除某用户的验证码Code
     */
    public static void removePhoneCodeByPhoneNum(String phoneNum) {
        if (USER_CODE_MAP.containsKey(phoneNum)) {
            USER_CODE_MAP.remove(phoneNum);
        }
    }

}
