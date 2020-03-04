package com.justbon.io.cha.test;

/**
 * @author ganli
 * @version 1.0
 * @file CopyFile.java
 * @Modified By：
 * @date 2020-03-04 下午3:19
 * @description
 */
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyFile {
    public static void main(String[] args) throws IOException {
        //创建输入流对象
        FileReader fr=new FileReader("C:\\Test\\copyfrom.txt");//文件不存在会抛出java.io.FileNotFoundException
        //创建输出流对象
        FileWriter fw=new FileWriter("C:\\Test\\copyto.txt");
        /*创建输出流做的工作：
         *         1、调用系统资源创建了一个文件
         *         2、创建输出流对象
         *         3、把输出流对象指向文件
         * */
        //文本文件复制，一次读一个字符
        method1(fr, fw);
        //文本文件复制，一次读一个字符数组
        method2(fr, fw);

        fr.close();
        fw.close();
    }

    public static void method1(FileReader fr, FileWriter fw) throws IOException {
        int ch;
        while((ch=fr.read())!=-1) {//读数据
            fw.write(ch);//写数据
        }
        fw.flush();
    }

    public static void method2(FileReader fr, FileWriter fw) throws IOException {
        char chs[]=new char[1024];
        int len=0;
        while((len=fr.read(chs))!=-1) {//读数据
            fw.write(chs,0,len);//写数据
        }
        fw.flush();
    }
}

