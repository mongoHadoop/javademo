package com.justbon.designmodel.proxy.dym.proxy2;
/**
 *    比如现在想为RealSubject这个类创建一个动态代理对象，JDK主要会做以下工作：
    1.   获取 RealSubject上的所有接口列表；
    2.   确定要生成的代理类的类名，默认为：com.sun.proxy.$ProxyXXXX ；
    3.   根据需要实现的接口信息，在代码中动态创建 该Proxy类的字节码；
    4 .  将对应的字节码转换为对应的class 对象；
    5.   创建InvocationHandler 实例handler，用来处理Proxy所有方法调用；
    6.   Proxy 的class对象 以创建的handler对象为参数，实例化一个proxy对象
 * @author lg
 *
 */
public class ProxyTest {
    
    public static void main(String[] args) {
    	
    	Person liudehua = new LiuDeHua();
        //中介代理机构
    	ProxyPeson proxy = new ProxyPeson(liudehua);
        //获得被代理对象
        Person p = proxy.getProxy();
        //调用代理对象的sing方法
        String retValue = p.sing("冰雨");
        System.out.println(retValue);
        //调用代理对象的dance方法
        String value = p.dance("江南style");
        System.out.println(value);
    }
}