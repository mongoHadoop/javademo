package com.justbon.designmodel.proxy.realydym;

import java.lang.reflect.Proxy;





public class Client {

    public static void main(String[] args) {

        Test test = (Test) Proxy.newProxyInstance(Test.class.getClassLoader(), new Class<?>[]{Test.class}, new TestProxy());
        test.say();
    }
}
/**
 *
 * 在第14行代码中,我们已经能够直接调用Test接口中的say方法了,
 * 原因就在于我们通过Proxy.newProxyInstance方法生成了一个代理类实例即TestProxy.
 **/