package com.justbon.thread.thread1.notify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mongo on 2018/8/11.
 * 用一句话来总结 一下 wait 和 notify： wait 使线程停止运行， 而 notify使停止的线程继续运行。
 */
public class Main {
    public static void main(String[] args) {
        List list = new ArrayList();
        ThreadA t1 = new ThreadA(list); // 注意在多线程 需要协作时候，调用了wait的实现类线程要写在代码前面。也就是 要wait的类 声明在前面（实际发现的问题）。
        t1.setName("A");
        t1.start();

        ThreadB t2 = new ThreadB(list);
        t2.setName("B");
        t2.start();
    }
}
