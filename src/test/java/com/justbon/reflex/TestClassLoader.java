package com.justbon.reflex;


/**
 * 根据运行结果得出Class.forName加载类是将类进了初始化，而ClassLoader的loadClass并没有对类进行初始化，只是把类加载到了虚拟机中。
 */
public class TestClassLoader {

    public static void main(String[] args) {
       try {
           Class.forName("com.justbon.reflex.ClassForName");
           System.out.println("###########分隔符(上面是class.forname)的加载过程,下面是classloader的加载过程###############");
           ClassLoader.getSystemClassLoader().loadClass("com.justbon.reflex.ClassForName");
       }catch (ClassNotFoundException e){
           e.printStackTrace();
       }
        /***
         * 根据运行结果得出Class.forName加载类是将类进了初始化，而ClassLoader的loadClass并没有对类进行初始化，只是把类加载到了虚拟机中。
         */


    }
}
