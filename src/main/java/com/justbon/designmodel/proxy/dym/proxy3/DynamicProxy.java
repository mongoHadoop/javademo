package com.justbon.designmodel.proxy.dym.proxy3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * 代理类
 * @author lg
 *   比如现在想为RealSubject这个类创建一个动态代理对象，JDK主要会做以下工作：
    1.   获取 RealSubject上的所有接口列表；
    2.   确定要生成的代理类的类名，默认为：com.sun.proxy.$ProxyXXXX ；
    3.   根据需要实现的接口信息，在代码中动态创建 该Proxy类的字节码；
    4 .  将对应的字节码转换为对应的class 对象；
    5.   创建InvocationHandler 实例handler，用来处理Proxy所有方法调用；
    6.   Proxy 的class对象 以创建的handler对象为参数，实例化一个proxy对象
        DynamicProxy 是InvocationHandler的实现类，也就是处理器
        代理对象执行被代理对象调用的地方
 */

public class DynamicProxy implements InvocationHandler
{
    //　这个就是我们要代理的真实对象
    private Object subject;
    
    // 构造方法，给我们要代理的真实对象赋初值
    public DynamicProxy(Object subject)
    {
        this.subject = subject;
    }
    
    @Override
    public Object invoke(Object object, Method method, Object[] args)
            throws Throwable
    {
        //　　在代理真实对象前我们可以添加一些自己的操作
        System.out.println("before rent house");
        
        System.out.println("Method:" + method);
        
        //    当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        method.invoke(subject, args);
        
        //　　在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("after rent house");
        
        return null;
    }

}