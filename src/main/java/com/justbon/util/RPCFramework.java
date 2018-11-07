package com.justbon.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现一个RPC框架，主要有两个功能：
 *     ① 供服务端暴露服务：export方法
 *     ② 供客户端引用服务：refer方法
 */
public class RPCFramework {
    /**
     * 暴露服务
     * @param service 服务实现
     * @param port 服务端口
     */
    @SuppressWarnings("resource")
    public static void export(final Object service, int port) throws IOException {
        // ① 创建serverSocket，等待客户端连接
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Export service " + service.getClass().getName() + " on port " + port);

        while (true) {
            // ② 对每一个客户端连接，新建一个线程进行处理
            final Socket socket = serverSocket.accept();
            new Thread(new Runnable() {
                public void run() {
                    try {
                        // ③ 依次解析客户端的请求参数（通信协议），包括服务方法、参数类型数组、参数值数组
                        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                        String methodName = in.readUTF();
                        Class<?>[] parameterTypes = (Class<?>[]) in.readObject();
                        Object[] arguments = (Object[]) in.readObject();

                        // ④ 服务提供方根据请求参数，执行相应的服务方法，并将结果返回
                        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                        Method method = service.getClass().getMethod(methodName, parameterTypes);
                        Object result = method.invoke(service, arguments);
                        out.writeObject(result);

                        out.close();
                        in.close();
                        socket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /**
     * 引用服务
     * <T> 接口泛型
     * @param interfaceClass 接口类型
     * @param host RPC服务器主机名
     * @param port RPC服务器端口
     * @return 远程服务的代理对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T refer(final Class<T> interfaceClass, final String host, final int port) {
        System.out.println("Get remote service " + interfaceClass.getName() + " from server " + host + ":" + port);

        // （☆）RPC客户端获取的是远程服务的代理对象
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] { interfaceClass },
                new InvocationHandler() {
                    // ① 每当客户端调用服务方法时，会触发invoke方法
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // ② 和服务端建立连接
                        Socket socket = new Socket(host, port);

                        // ③ 向服务端发送请求参数（通信协议），包括服务方法、参数类型数组、参数值数组
                        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                        out.writeUTF(method.getName());
                        out.writeObject(method.getParameterTypes());
                        out.writeObject(args);

                        // ④ 接收并返回服务端的返回值
                        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                        Object result = in.readObject();

                        in.close();
                        out.close();
                        socket.close();

                        return result;
                    }
                });
    }
}