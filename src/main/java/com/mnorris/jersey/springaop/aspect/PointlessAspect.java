package com.mnorris.jersey.springaop.aspect;

import javax.inject.Named;
import javax.inject.Singleton;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Named
@Singleton
@Aspect
public class PointlessAspect {
	@Pointcut("within(@com.mnorris.jersey.springaop.aspect.Pointless *) && execution(public * *(..))")
	private void publicMethodWithinAnnotatedClass() {
	}

	@Pointcut("@annotation(com.mnorris.jersey.springaop.aspect.Pointless)")
	private void annotatedMethod() {
	}

	@Pointcut("@annotation(javax.annotation.PostConstruct)")
	private void postConstructMethod() {
	}

	@Around("(annotatedMethod() || publicMethodWithinAnnotatedClass()) && !postConstructMethod()")
	public Object invoke(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("Pointless aspect called");
		return pjp.proceed();
	}
}
