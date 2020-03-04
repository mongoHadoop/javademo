package com.justbon.io.nio;

/**文件转移
 * @author ganli
 * @version 1.0
 * @file FileTransfer.java
 * @Modified By：
 * @date 2020-03-04 下午4:44
 * @description
 * NIO 提供transferTo tansferFrom, 和传统的文件访问方式相比减少了数据从内核到用户空间的复制，数据直接在内核移动，在Linux系统中使用sendfile系统调用
 * 这里分别通过FileChannel.transferFrom 和Files.copy以及普通的io调用实现文件的复制
 */


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileTransfer {
    Path fromPath = Paths.get("from/test.tmp");
    Path toPath = Paths.get("to/test.tmp");

    public static void main(String[] args) throws IOException {
        FileTransfer transfer = new FileTransfer();
        long start = System.currentTimeMillis();
        transfer.transferFrom();
        long end1 = System.currentTimeMillis();
        System.out.println("transferFrom: "+ (end1 - start));
        Files.deleteIfExists(transfer.toPath);

        transfer.copy();
        long end2 = System.currentTimeMillis();
        System.out.println("Files copy: "+(end2 - end1));
        Files.deleteIfExists(transfer.toPath);
        transfer.ioCopy();
        long end3 = System.currentTimeMillis();
        System.out.println("original io: "+(end3 - end2));
    }

    /**
     * nio拷贝
     */
    void transferFrom() {
        try (FileChannel channelFrom = FileChannel.open(fromPath, StandardOpenOption.READ);
             FileChannel channelTo = FileChannel.open(toPath, StandardOpenOption.CREATE_NEW,
                     StandardOpenOption.WRITE);) {
            channelTo.transferFrom(channelFrom, 0L, channelFrom.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件之间拷贝
     */
    void copy() {
        try {
            Files.copy(fromPath, toPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void ioCopy() {
        try (InputStream is = new FileInputStream(fromPath.toFile());
             OutputStream os = new FileOutputStream(toPath.toFile());) {
            byte[] buffer = new byte[4096];
            int byteread = 0;
            while ((byteread = is.read(buffer)) != -1) {
                os.write(buffer, 0, byteread);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}