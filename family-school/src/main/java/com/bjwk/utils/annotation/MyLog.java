package com.bjwk.utils.annotation;
import java.lang.annotation.*;

import org.springframework.core.annotation.Order;

@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.METHOD,ElementType.TYPE})  
@Documented  
//优先级
//@Order(5)
public @interface MyLog {
	String description() default "";
}