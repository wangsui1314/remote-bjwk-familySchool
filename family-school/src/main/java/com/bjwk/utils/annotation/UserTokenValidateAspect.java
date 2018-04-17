package com.bjwk.utils.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bjwk.utils.CallStatusEnum;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.RedisClient;

import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
/**
 * 
 * @ClassName: UserTokenValidateAspect 
 * @Description: TODO("验证用户token") 
 * @author: lihui 
 * @date: 2018年4月17日 下午11:57:30
 */
@Aspect
@Component
public class UserTokenValidateAspect {

	//token验证层controller切入点
	@Pointcut("@annotation(com.bjwk.utils.annotation.TokenValidate)")
	public void tokenAspect(){

	}

	/**
	 * 验证token
	 * @param joinpoint
	 * @throws Exception 
	 */
	@Around("tokenAspect()")
	public Object around(ProceedingJoinPoint joinpoint) throws Exception{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String token=request.getParameter("token");
		//Object result=null;
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		/**
		 * 1.验证该用户是否已登录，通过是否包含此token来判断
		 */
		Jedis  jedis=RedisClient.getInstance().getJedis();
		String userName=jedis.hget("loginStatus", token);

		if(userName!=null){

			try {
            joinpoint.proceed();//放行
			} catch (Throwable e) {
				e.printStackTrace();
				throw new Exception();
			}
		}else{
			dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			dataWrapper.setMsg("token验证失败");
		}
		return dataWrapper;
	}	
}
