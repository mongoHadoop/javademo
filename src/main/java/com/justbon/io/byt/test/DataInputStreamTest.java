package com.justbon.io.byt.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.SecurityException;

/**
 * 有时没有必要存储整个对象的信息，而只是要存储一个对象的成员数据，
 * 成员数据的类型假设都是Java的基本数据类型，这样的需求不必使用到与Object输入、
 * 输出相关的流对象，可以使用DataInputStream、DataOutputStream来写入或读出数据。
 * 下面是一个例子：（DataInputStream的好处在于在从文件读出数据时，
 * 不用费心地自行判断读入字符串时或读入int类型，
 * 使用对应的readUTF()和readInt()方法就可以正确地读入完整的类型数据。）
 *
 */
public class DataInputStreamTest {

    private static final int LEN = 5;

    public static String path ="D:/home/gl/member2.txt";
    public static void main(String[] args) {
        // 测试DataOutputStream，将数据写入到输出流中。
        testDataOutputStream() ;
        // 测试DataInputStream，从上面的输出流结果中读取数据。
        testDataInputStream() ;
    }

    /**
     * DataOutputStream的API测试函数
     */
    private static void testDataOutputStream() {
    	
        try {
            File file = new File(path);
            DataOutputStream out =
                  new DataOutputStream(
                      new FileOutputStream(file));

            out.writeBoolean(true);
            out.writeByte((byte)0x41);
            out.writeChar((char)0x4243);
            out.writeShort((short)0x4445);
            out.writeInt(0x12345678);
            out.writeLong(0x0FEDCBA987654321L);

            out.writeUTF("abcdefghijklmnopqrstuvwxyz严12");

            out.close();
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (SecurityException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
    /**
     * DataInputStream的API测试函数
     */
    private static void testDataInputStream() {

        try {
            File file = new File(path);
            DataInputStream in =
                  new DataInputStream(
                      new FileInputStream(file));

            System.out.printf("byteToHexString(0x8F):0x%s\n", byteToHexString((byte)0x8F));
            System.out.printf("charToHexString(0x8FCF):0x%s\n", charToHexString((char)0x8FCF));

            System.out.printf("readBoolean():%s\n", in.readBoolean());
            System.out.printf("readByte():0x%s\n", byteToHexString(in.readByte()));
            System.out.printf("readChar():0x%s\n", charToHexString(in.readChar()));
            System.out.printf("readShort():0x%s\n", shortToHexString(in.readShort()));
            System.out.printf("readInt():0x%s\n", Integer.toHexString(in.readInt()));
            System.out.printf("readLong():0x%s\n", Long.toHexString(in.readLong()));
            System.out.printf("readUTF():%s\n", in.readUTF());

            in.close();
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (SecurityException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    // 打印byte对应的16进制的字符串
    private static String byteToHexString(byte val) {
        return Integer.toHexString(val & 0xff);
    }

    // 打印char对应的16进制的字符串
    private static String charToHexString(char val) {
        return Integer.toHexString(val);
    }

    // 打印short对应的16进制的字符串
    private static String shortToHexString(short val) {
        return Integer.toHexString(val & 0xffff);
    }
}