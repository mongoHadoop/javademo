package com.justbon.cglib;

/**
 * @author ganli
 * @version 1.0
 * @file CgLibProxy.java
 * @Modified By：
 * @date 2020-06-11 上午10:25
 * @description
 */

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * 说白了 Enhancer 是字节码增强器,它创建了代理对象Proxy 继承了被代理目标对象的方法，重写了目标对象的方法添加了字节码增强器.
 */
public class CgLibProxy {
    public static void main(String[] args) {
        //在指定目录下生成动态代理类，我们可以反编译看一下里面到底是一些什么东西
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/home/mongo/workspace");

        //创建Enhancer对象，类似于JDK动态代理的Proxy类，下一步就是设置几个参数
        Enhancer enhancer = new Enhancer();
        //设置目标类的字节码文件
        enhancer.setSuperclass(Dog.class);
        //设置回调函数
        enhancer.setCallback(new MyMethodInterceptor());

        //这里的creat方法就是正式创建代理类
        Dog proxyDog = (Dog)enhancer.create();
        //调用代理类的eat方法
        proxyDog.eat();
    }
}