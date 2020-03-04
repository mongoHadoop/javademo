package com.justbon.io.cha.buffer;

import java.io.*;

/**
 * @author ganli
 * @version 1.0
 * @file CopyFileBuffer.java
 * @Modified By：
 * @date 2020-03-04 下午3:32
 * @description
 */
public class CopyFileBuffer {
    public static void main(String[] args) throws IOException {
        //创建输入流对象
        BufferedReader br=new BufferedReader(new FileReader("C:\\Test\\copyfrom.txt"));//文件不存在会抛出java.io.FileNotFoundException
        //创建输出流对象
        BufferedWriter bw=new BufferedWriter(new FileWriter("C:\\Test\\copyto.txt"));
        //文本文件复制
        char [] chs=new char[1024];
        int len=0;
        while((len=br.read(chs))!=-1) {
            bw.write(chs, 0, len);
        }
        //释放资源
        br.close();
        bw.close();
    }
}
