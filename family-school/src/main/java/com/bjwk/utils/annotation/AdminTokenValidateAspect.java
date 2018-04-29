package com.bjwk.utils.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bjwk.dao.UtilsDao;
import com.bjwk.utils.BeanUtil;
import com.bjwk.utils.CallStatusEnum;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.RedisUtil;


import javax.servlet.http.HttpServletRequest;
/**
 * 
 * @ClassName: UserTokenValidateAspect 
 * @Description: TODO("验证用户管理员权限") 
 * @author: lihui 
 * @date: 2018年4月17日 下午11:57:30
 */
@Aspect
@Component
public class AdminTokenValidateAspect {

	@Autowired
	private UtilsDao utilsDao;
	//token验证层controller切入点
	@Pointcut("@annotation(com.bjwk.utils.annotation.AdminTokenValidate)")
	public void tokenAspect(){

	}

	/**
	 * 验证adminToken
	 * @param joinpoint
	 * @throws Throwable 
	 */
	@Around("tokenAspect()")
	public Object around(ProceedingJoinPoint joinpoint) throws Throwable{
		
		System.out.println("进来了");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String adminToken=request.getParameter("adminToken");
		//Object result=null;
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		/**
		 * 1.验证该管理员是否已登录，是否有权限进行操作
		 */
//		Jedis  jedis=RedisClient.getInstance().getJedis();
//		String userName=jedis.hget("loginStatus", token);
		RedisUtil redisUtil= BeanUtil.getBean("redisUtil");
    	RedisTemplate<String, Object> redisTemplate=redisUtil.getRedisTemplate();
    	String userName=(String) redisTemplate.opsForValue().get(adminToken);
    	
    	if(userName==null) {
    		dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			dataWrapper.setMsg("未登录");
			return dataWrapper;
    	}else {
    		
    		Integer sign=utilsDao.queryAdminUserSign(userName);
    		System.out.println(sign);
    		if(sign<3) {
    			dataWrapper.setCallStatus(CallStatusEnum.FAILED);
    			dataWrapper.setMsg("权限异常");
    			return dataWrapper;
    		}
    	}
    	 joinpoint.proceed();//放行
		return dataWrapper;
	}	
}
