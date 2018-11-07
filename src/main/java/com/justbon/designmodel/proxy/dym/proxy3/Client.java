package com.justbon.designmodel.proxy.dym.proxy3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/***
 *   当在代码阶段规定这种代理关系，
 *   Proxy类通过编译器编译成class文件，当系统运行时，此class已经存在了。
 *   这种静态的代理模式固然在访问无法访问的资源，增强现有的接口业务功能方面有很大的优点，
 *   但是大量使用这种静态代理，会使我们系统内的类的规模增大，并且不易维护；
 *   并且由于Proxy和RealSubject的功能 本质上是相同的，
 *   Proxy只是起到了中介的作用，这种代理在系统中的存在，
 *   导致系统结构比较臃肿和松散。
 *          为了解决这个问题，就有了动态地创建Proxy的想法：在运行状态中，
 *          需要代理的地方，根据Subject 和RealSubject，动态地创建一个Proxy，
 *          用完之后，就会销毁，这样就可以避免了Proxy 角色的class在系统中冗杂的问题了。
 *          
 *          
 *             比如现在想为RealSubject这个类创建一个动态代理对象，JDK主要会做以下工作：
    1.   获取 RealSubject上的所有接口列表；
    2.   确定要生成的代理类的类名，默认为：com.sun.proxy.$ProxyXXXX ；
    3.   根据需要实现的接口信息，在代码中动态创建 该Proxy类的字节码；
    4 .  将对应的字节码转换为对应的class 对象；
    5.   创建InvocationHandler 实例handler，用来处理Proxy所有方法调用；
    6.   Proxy 的class对象 以创建的handler对象为参数，实例化一个proxy对象

 * @author lg
 *
 */
public class Client
{
    public static void main(String[] args)
    {
        //    我们要代理的真实对象
        Subject realSubject = new RealSubject();

        //    我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
        InvocationHandler handler = new DynamicProxy(realSubject);

        /*
         * 通过Proxy的newProxyInstance方法来创建我们的代理对象，我们来看看其三个参数
         * 第一个参数 handler.getClass().getClassLoader() ，我们这里使用handler这个类的ClassLoader对象来加载我们的代理对象
         * 第二个参数realSubject.getClass().getInterfaces()，我们这里为代理对象提供的接口是真实对象所实行的接口，表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了
         * 第三个参数handler， 我们这里将这个代理对象关联到了上方的 InvocationHandler 这个对象上
         */
        Subject subject = (Subject)Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject
                .getClass().getInterfaces(), handler);
        
        System.out.println(subject.getClass().getName());
        subject.rent();
        subject.hello("world");
    }
}