package com.justbon.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author ganli
 * @version 1.0
 * @file MyMethodInterceptor.java
 * @Modified By：
 * @date 2020-06-11 上午10:24
 * @description
 */
public class MyMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("这里是对目标类进行增强！！！");
        //注意这里的方法调用，不是用反射哦！！！
        Object object = proxy.invokeSuper(obj, args);
        return object;
    }
}