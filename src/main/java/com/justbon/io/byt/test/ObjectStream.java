package com.justbon.io.byt.test;

import java.io.*;  

/**
 * 
 * 存储对象文件
 * 读写对象：ObjectInputStream 和ObjectOutputStream ，该流允许读取或写入用户自定义的类，
 * 但是要实现这种功能，被读取和写入的类必须实现Serializable接口，
 * 其实该接口并没有什么方法，可能相当于一个标记而已，但是确实不合缺少的。实例代码如下：
 * 
 */

public class ObjectStream {  
   
	public static String tragetObjectFile = "D:/home/gl/student.txt";
   public static void main(String[] args) {  
      // TODO自动生成的方法存根  
      ObjectOutputStream objectwriter=null;  
      ObjectInputStream objectreader=null;  
       
      try {  
         objectwriter=new ObjectOutputStream(new FileOutputStream(tragetObjectFile));  
         objectwriter.writeObject(new Student("java", 22));  
         objectwriter.writeObject(new Student("hadoop", 18));  
         objectwriter.writeObject(new Student("c#", 17));  
         objectreader=new ObjectInputStream(new FileInputStream(tragetObjectFile));  
         for (int i = 0; i < 3; i++) {  
            System.out.println(objectreader.readObject());  
         }  
      } catch (IOException | ClassNotFoundException e) {  
         // TODO自动生成的 catch 块  
         e.printStackTrace();  
      }finally{  
         try {  
            objectreader.close();  
            objectwriter.close();  
         } catch (IOException e) {  
            // TODO自动生成的 catch 块  
            e.printStackTrace();  
         }  
          
      }  
       
   }  
   
}  
class Student implements Serializable{  
   private String name;  
   private int age;  
    
   public Student(String name, int age) {  
      super();  
      this.name = name;  
      this.age = age;  
   }  
   
   @Override  
   public String toString() {  
      return "Student [name=" + name + ", age=" + age + "]";  
   }  
    
    
} 