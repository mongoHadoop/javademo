package com.justbon.io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  @Description:
 *  @author:xyq
 *  @date:2018年6月6日
 */
public class Server {
    ServerSocket ss = null;

    public static void main(String[] args) {
        new Server().startServer();
    }
    public void startServer() {
        Socket socket = null;
        try {
            ss = new ServerSocket(1234); // 监听1234端口
            while (true) {
                socket = ss.accept();   // 堵塞式接受Socket
                new ServerThread(socket).start();  // 放入线程进行处理
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ServerThread extends Thread {
        Socket socket = null;
        public ServerThread(Socket socket){
            this.socket = socket;
        }
        /* (non-Javadoc)
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
            BufferedReader br = null;
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(socket.getOutputStream());
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (true) {
                    String line = br.readLine();
                    System.out.println("从客户端来的信息：" + line);
                    pw.println("你好，服务器已经收到您的信息！'" + line + "'\n");
                    pw.flush();
                    System.out.println("已经返回给客户端！");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    pw.close();
                    br.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}