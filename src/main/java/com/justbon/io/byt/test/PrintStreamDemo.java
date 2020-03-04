package com.justbon.io.byt.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * @author ganli
 * @version 1.0
 * @file PrintStreamDemo.java
 * @Modified By：
 * @date 2020-03-04 下午3:43
 * @description
 */
public class PrintStreamDemo {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("copy.java"));
        PrintStream ps=new PrintStream("printcopy2.java");
        String line;
        while((line=br.readLine())!=null) {
            ps.println(line);
        }
        br.close();
        ps.close();
    }
}