package com.justbon.aspectj;

/**
 * @author ganli
 * @version 1.0
 * @file ProfilingAspect.java
 * @Modified By：
 * @date 2020-01-14 下午4:40
 * @description
 */

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ProfilingAspect {

    @Pointcut("execution(* com.javadoop.aspectjlearning.model.*.*(..))")
    public void modelLayer() {
    }

    @Around("modelLayer()")
    public Object logProfile(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        System.out.println("[ProfilingAspect]方法: 【" + joinPoint.getSignature() + "】结束，用时: " + (System.currentTimeMillis() - start));

        return result;
    }
}