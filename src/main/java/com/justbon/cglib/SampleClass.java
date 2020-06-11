package com.justbon.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author ganli
 * @version 1.0
 * @file SampleClass.java
 * @Modified By：
 * @date 2020-06-11 上午9:21
 * @description
 *
 * CGLib动态代理是代理类去继承目标类，然后重写其中目标类的方法啊，这样也可以保证代理类拥有目标类的同名方法；
 */
public class SampleClass {
    public void test(){
        System.out.println("hello world");
    }
    public String test2(Object o){
       return "hello world";
    }
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before method run...");
                //注意这里的方法调用，不是用反射哦！！！
                Object result = proxy.invokeSuper(obj, args);
                System.out.println("after method run...");
                return result;
            }
        });
        SampleClass sample = (SampleClass) enhancer.create();
        sample.test();
    }
}