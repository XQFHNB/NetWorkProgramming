package com.xqf.tcp;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by XQF on 2016/12/3.
 */
public class SeverSocketTest {
    SeverSocketTest() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(2010);
            System.out.println("服务器端口：" + serverSocket.getLocalPort());
            System.out.println("服务器地址：" + serverSocket.getInetAddress());
            System.out.println("服务器套接字：" + serverSocket.getLocalSocketAddress());
            System.out.println("是否绑定连接：" + serverSocket.isBound());
            System.out.println("是否关闭：" + serverSocket.isClosed());
            System.out.println("服务器套接字详情：" + serverSocket.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SeverSocketTest();
    }
}
