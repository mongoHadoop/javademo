package com.justbon.io.byt.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 注意：随机读写。数据需要规律。用RandomAccessFile类需要明确要操作的数据的位置。
 *
 */
public class RandomAccessFileDemo {
    public static void main(String[] args) throws IOException {
        //writeFile();
        RandomAccessFile raf=new RandomAccessFile("tempFile\\test.txt","r");
        raf.seek(8*1);
        //读第二个人
        byte[] buf=new byte[4];
        raf.read(buf);
        String name=new String(buf);
        System.out.println("name="+name);

        int age=raf.readInt();
        System.out.println("age="+age);
        raf.close();
    }

    public static void writeFile() throws FileNotFoundException, IOException {
        //明确要操作的位置，可以多个线程操作同一份文件而不冲突。多线程下载的基本原理。
        RandomAccessFile raf=new RandomAccessFile("tempFile\\test.txt","rw");
        raf.write("张三".getBytes());
        raf.writeInt(97);
        //保证字节原样性
        raf.write("李四".getBytes());
        raf.writeInt(99);
        //保证字节原样性
        System.out.println(raf.getFilePointer());
        //获取随机指针
        raf.seek(8*2);
        //设置指针
        raf.write("王五".getBytes());
        raf.writeInt(100);
        //保证字节原样性
        raf.close();
    }
}