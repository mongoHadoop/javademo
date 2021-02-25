package com.justbon.designmodel.proxy.realydym;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author ganli
 * @version 1.0
 * @file TestProxy.java
 * @Modified By：
 * @date 2021-01-28 下午3:25
 * @description
 */
public class TestProxy implements InvocationHandler {


    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        if (method.getName().equals("say")){
            System.out.println("hello world");
        }
        return null;
    }

}

/***
 * 需要实现invoke方法，意思就是“调用”的意思（当然我们想要调用接口中的方法时并不会显示调用invoke）
 * 。我们从第19行看到，当调用的方法是say时，输出“hello world”。有了这个TestProxy.java代理类过后，我们再来客户端代码中测试。
 */
