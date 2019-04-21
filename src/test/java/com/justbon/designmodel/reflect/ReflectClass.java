package com.justbon.designmodel.reflect;

import com.justbon.designmodel.reflect.bean.Book;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
public class ReflectClass {

    private String classPath="com.justbon.designmodel.reflect.bean.Book";
    // 创建对象
    @Test
    public  void reflectNewInstance() {
        try {
            Class<?> classBook = Class.forName(classPath);
            Object objectBook = classBook.newInstance();
            Book book = (Book) objectBook;
            book.setName("Android进阶之光");
            book.setAuthor("刘望舒");
            System.out.println("reflectNewInstance book ={} "+book.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @Test
    // 反射私有的构造方法
    public  void reflectPrivateConstructor() {
        try {
            Class<?> classBook = Class.forName(classPath);
            Constructor<?> declaredConstructorBook = classBook.getDeclaredConstructor(String.class,String.class);
            declaredConstructorBook.setAccessible(true);
            Object objectBook = declaredConstructorBook.newInstance("Android开发艺术探索","任玉刚");
            Book book = (Book) objectBook;
            System.out.println("reflectPrivateConstructor book = " + book.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    // 反射私有属性
    public  void reflectPrivateField() {
        try {
            Class<?> classBook = Class.forName(classPath);
            Object objectBook = classBook.newInstance();
            Field fieldTag = classBook.getDeclaredField("TAG");
            fieldTag.setAccessible(true);
            String tag = (String) fieldTag.get(objectBook);
            System.out.println("reflectPrivateField tag = " + tag);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 反射私有方法
    @Test
    public   void reflectPrivateMethod() {
        try {
            Class<?> classBook = Class.forName(classPath);
            Method methodBook = classBook.getDeclaredMethod("declaredMethod",int.class);
            methodBook.setAccessible(true);
            Object objectBook = classBook.newInstance();
            String string = (String) methodBook.invoke(objectBook,0);
            System.out.println("reflectPrivateMethod string = " + string);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}