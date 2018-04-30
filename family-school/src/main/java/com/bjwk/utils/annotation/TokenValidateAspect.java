package com.bjwk.utils.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bjwk.utils.BeanUtil;
import com.bjwk.utils.CallStatusEnum;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.RedisClient;

import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
/**
 * 
 * @ClassName: UserTokenValidateAspect s
 * @Description: TODO("验证用户token") 
 * @author: lihui 
 * @date: 2018年4月17日 下午11:57:30
 */
@Aspect
@Component
public class TokenValidateAspect {

	//token验证层controller切入点
	@Pointcut(value="@annotation(com.bjwk.utils.annotation.TokenValidate)")
	public void tokenAspect(){

	}
	/**
	 * 验证token
	 * @param joinpoint
	 * @throws Throwable 
	 */
	@Around("tokenAspect()")
	public Object around(ProceedingJoinPoint joinpoint) throws Throwable{
		 System.out.println("12321312");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String token=request.getParameter("token");
		 System.out.println(token);
		//Object result=null;
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		/**
		 * 1.验证该用户是否已登录，通过是否包含此token来判断
		 */
		Jedis  jedis=RedisClient.getJedis();
		String userName=jedis.hget("loginStatus", token);
		try {
			if(userName!=null){
	            joinpoint.proceed();//放行
			}else{
				dataWrapper.setCallStatus(CallStatusEnum.FAILED);
				dataWrapper.setMsg("token验证失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
//			jedis.close();
		}
		
		return dataWrapper;
	}	
}
