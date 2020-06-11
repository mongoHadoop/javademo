package com.justbon.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import org.junit.Test;

/**
 * @author ganli
 * @version 1.0
 * @file CGlibTest.java
 * @Modified By：
 * @date 2020-06-11 上午9:35
 * @description
 */
public class CGlibTest {


    @Test
    public void testFixedValue(){
        Enhancer enhancer = new Enhancer();
        /**
         * Enhancer.setSuperclass用来设置父类型,
         * 从toString方法可以看出，使用CGLIB生成的类为被代理类的一个子类,
         * 形如: SampleClass$$EnhancerByCGLIB$$e3ea9b7
         *
         */
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "Hello cglib";
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        System.out.println(proxy.test2(null)); //拦截test，输出Hello cglib
        System.out.println(proxy.toString());
        System.out.println(proxy.getClass());
        System.out.println(proxy.hashCode());
    }
}
