package com.andon.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Greeting {
	@Pointcut("execution(* com.andon.controller.*.*(..))")  
    private void anyMethod(){}//定义一个切入点  
	
//    @Before("within(com.andon..*)")  
//    public void doAccessCheck(){   
//        System.out.println("前置通知");  
//    }  
//      
//    @AfterReturning("within(com.andon.controller..*)")  
//    public void doAfter(){  
//        System.out.println("后置通知");  
//    }
    
    @Before("anyMethod()")  
    public void doAccessCheck(){   
        System.out.println("前置通知");  
    }  
      
    @AfterReturning("anyMethod()")  
    public void doAfter(){  
        System.out.println("后置通知");  
    }  
      
    @After("anyMethod()")  
    public void after(){  
        System.out.println("最终通知");  
    }  
      
    @AfterThrowing(pointcut = "anyMethod()", throwing="e")  
    public void doAfterThrow(JoinPoint jp,Throwable e){  
    	System.out.println(e.getMessage());
        System.out.println("例外通知");  
        
    }  
      
    @Around("anyMethod()")  
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{  
        System.out.println("进入环绕通知");  
        Object object = pjp.proceed();//执行该方法  
        System.out.println("退出方法");  
        return object;  
    }  
}
