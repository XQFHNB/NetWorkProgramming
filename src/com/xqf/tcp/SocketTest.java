package com.xqf.tcp;

import java.net.Socket;

/**
 * Created by XQF on 2016/12/3.
 */
public class SocketTest {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("218.198.118.103", 80);
            System.out.println("是否绑定连接：" + socket.isBound());
            System.out.println("本地端口:" + socket.getLocalPort());
            System.out.println("连接服务器的端口："+socket.getPort());
            System.out.println("连接服务器的地址:" + socket.getInetAddress());
            System.out.println("连接远程服务的套接字：" + socket.getRemoteSocketAddress());
            System.out.println("是否处于连接状态：" + socket.isConnected());
            System.out.println("客户套接详情：" + socket.toString());
        } catch (Exception e) {
            System.out.println("服务器没有启动！");
        }
    }
}



