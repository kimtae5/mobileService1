package com.kt5.mobileservice1.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAop {
	
	//모든 POST 요청에 대해서 반응하는 메서드
	@Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
	public void postMapping() {}
	
	@AfterReturning(pointcut = "postMapping()", returning = "result")
	public void afterReturninge(JoinPoint joinPoint, Object result) {
	System.out.println("PostMapping");
	}
	
	//Controller의 메서드에 대해서 반응
	@Pointcut("execution(* com.kt5.mobileservice1.controller.*.*(..))")
	public void thing() {
	}
	
	@Around("thing()")
	public Object doStuffBeforeThing(ProceedingJoinPoint joinPoint) {
		Class clazz = joinPoint.getTarget().getClass();
		Logger logger = LoggerFactory.getLogger(clazz);
		Object result = null;
		try {
			logger.info("Controller의 작업을 처리하기 전에 수행할 내용");
			//호출한 메서드를 실행
			result = joinPoint.proceed(joinPoint.getArgs());
			logger.info("Controller의 작업을 처리한 후 수행할 내용");
		}catch(Throwable e) {
			System.out.println(e.getLocalizedMessage());
		}
		return result;
	}
	
}
