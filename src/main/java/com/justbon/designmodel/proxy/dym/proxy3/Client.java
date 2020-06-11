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
         *
         * ClassLoader, 用于加载代理类的 Loader 类，通常这个 Loader 和被代理的类是同一个 Loader 类。
           Interfaces,  是要被代理的那些那些接口。
           InvocationHandler, 就是用于执行除了被代理接口中方法之外的用户自定义的操作,
                  他也是用户需要代理的最终目的。用户调用目标方法都被代理到 InvocationHandler 类中定义的唯一方法 invoke 中.
         *
         */
            //创建代理实例类 真实的代理对象
        Subject proxySubject = (Subject)Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject
                .getClass().getInterfaces(), handler);

        /*
         * newProxyInstance
         * 获取代理类
         * Class cl = getProxyClass(loader, interfaces);
         * 获取带有InvocationHandler参数的构造方法
         * Constructor cons = cl.getConstructor(constructorParams);
         * 把handler传入构造方法生成实例
         * return (Object) cons.newInstance(new Object[] { h });
         */


        /***
         * 一个典型的动态代理创建对象过程可分为以下四个步骤：
         *
         * 1、通过实现InvocationHandler接口创建调用处理器
         *
         * IvocationHandler handler = new InvocationHandlerImpl(...);
         * 2、通过为Proxy类指定ClassLoader对象和一组interface创建动态代理类
         *
         * Class clazz = Proxy.getProxyClass(classLoader,new Class[]{...});
         * 3、通过反射机制获取动态代理类的构造函数，其参数类型是调用处理器接口类型
         *
         * Constructor constructor = clazz.getConstructor(new Class[]{InvocationHandler.class});
         * 4、通过构造函数创建代理类实例，此时需将调用处理器对象作为参数被传入
         *
         * Interface Proxy = (Interface)constructor.newInstance(new Object[] (handler));
         * 为了简化对象创建过程，Proxy类中的newProxyInstance方法封装了2~4，只需两步即可完成代理对象的创建。
         *
         * Subject proxySubject = (Subject)Proxy.newProxyInstance(Subject.class.getClassLoader(),
         *         new Class[]{Subject.class}, new InvocationHandlerImpl (real));
         *
         *    生成的proxySubject继承Proxy类实现Subject接口。
         *    实现的Subject的方法实际是调用处理器的invoke方法，
         *    而invoke方法利用反射调用的是被代理对象的方法（Object result=method.invoke(proxied,args)）；
         * 重点Proxy.newProxyInstance，源码分析，会在其他文档中单独总结记录。
         * 类Proxy的getProxyClass方法调用ProxyGenerator的 generateProxyClass方法产生ProxySubject.class的二进制数据。
         */


        System.out.println("subject name: "+proxySubject.getClass().getName());
        proxySubject.rent();
        //proxySubject.hello("world");
    }
}