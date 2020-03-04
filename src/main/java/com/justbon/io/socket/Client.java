package com.justbon.io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *  @Description:
 *  @author:xyq
 *  @date:2018年6月6日
 */
public class Client {
    public static void main(String[] args) {
        new Client().startClient();
    }

    public void startClient() {
        Socket socket = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            socket = new Socket("localhost", 1234);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream());
            String get = "客户端" + Math.random() * 1000;

            while (true) {
                if (get != null && get.length() > 0) {
                    pw.println(get);   // 写入缓冲流
                    pw.flush();         // 刷新缓冲
                }
                if (br != null) {
                    String line = br.readLine();
                    System.out.println("从服务器来的信息：" + line);
                }

                Thread.sleep(2000);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
