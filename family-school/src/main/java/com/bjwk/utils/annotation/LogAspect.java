package com.bjwk.utils.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect  
@Component  
public class LogAspect {
	 /* 
	    * 在执行上面其他操作的同时也执行这个方法 
	    * */  
	    @Around("@annotation(com.bjwk.utils.annotation.TestLog)")  
	    public void aroundExec(ProceedingJoinPoint pjp) throws Throwable{  
	        System.out.println("我是Around，来打酱油的");  
	        pjp.proceed();  
	    }  
}
