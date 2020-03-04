package com.justbon.io.cha.test;

/**
 * @author ganli
 * @version 1.0
 * @file PrintWriterDemo.java
 * @Modified By：
 * @date 2020-03-04 下午3:37
 * @description
 */
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * 注意：创建FileWriter对象时boolean参数表示是否追加；
 *              而创建打印流对象时boolean参数表示是否自动刷新
 * 1)向文本输出流打印对象的格式化表示形式。此类实现在 PrintStream 中的所有 print 方法。不能输出字节，但是可以输出其他任意类型。
 *
 * (2)与 PrintStream 类不同，如果启用了自动刷新，则只有在调用 println、printf 或 format 的其中一个方法时才可能完成此操作，而不是每当正好输出换行符时才完成。这些方法使用平台自有的行分隔符概念，而不是换行符。
 *
 * (3)此类中的方法不会抛出 I/O 异常，尽管其某些构造方法可能抛出异常。客户端可能会查询调用 checkError() 是否出现错误。
 */
public class PrintWriterDemo {
    public static void main(String[] args) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("/home/mongo/workspace/respository2/javademo/src/main/java/com/justbon/io/cha/test/print.txt"), true);
        pw.write("测试打印流");
        pw.println("此句之后换行");
        pw.println("特有功能：自动换行和自动刷新");
        pw.println("利用构造器设置自动刷新");
        pw.close();
        System.out.printf("");
    }
}