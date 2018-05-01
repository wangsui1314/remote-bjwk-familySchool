package com.bjwk.utils.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

@Aspect
@Component
public class LogAspect {
    /*
     * 在执行上面其他操作的同时也执行这个方法
     * */
    //controller层切入点
    @Pointcut("@annotation(com.bjwk.utils.annotation.MyLog)")
    public void controllerAspect() {

    }

    @Before("controllerAspect()")
    public void doAfter(JoinPoint joinPoint) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String url=request.getRequestURI();
        String operate = getControllerMethodDescription(joinPoint);
        System.out.println("url: "+url);
        System.out.println("operate: "+operate);
        /**
         * some   code
         */

    }

    /**
     *
     * @param joinPoint
     * @return
     * @throws Exception
     */
    public String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {

            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(com.bjwk.utils.annotation.MyLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

}
