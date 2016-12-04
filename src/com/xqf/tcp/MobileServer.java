package com.xqf.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by XQF on 2016/12/3.
 */
public class MobileServer implements BusinessProtocal {
    private ServerSocket serverSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private int serviceId;
    private Socket socket;
    private int fee;
    private String str;

    public MobileServer() {
        try {
            serverSocket = new ServerSocket(2010);
            while (true) {
                System.out.println("服务器准备就绪，等待客户请求。。。。");
                socket = serverSocket.accept();//堵塞状态，除非有客户呼叫
                in = new DataInputStream(socket.getInputStream());//getInputStream（）返回的是InputStream,需要包装一下
                out = new DataOutputStream(socket.getOutputStream());
                while (true) {
                    try {
                        serviceId = in.readInt();//读取放入“线路里的信息”
                        switch (serviceId) {
                            case PAY_BILL:
                                paybill();//支付话费
                                break;
                            case ROAMING_SERVICE:
                                roamingService();//办理漫游
                            default:
                                break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户" + socket.getInetAddress().getHostName() + "业务办理完毕。已经离开了。。。。");
        }
    }

    @Override
    public void paybill() {
        try {
            fee = in.readInt();
            System.out.println("正在处理用户" + socket.getInetAddress().getHostName() + "预交饿的" + fee + "元话费请求！");
            System.out.println("交费处理完毕！");
            out.writeUTF("尊敬的客户，你已经成功预交了 " + fee + " 元话费");
            Thread.sleep(1000);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void roamingService() {
        try {
            str = in.readUTF();
            System.out.println("正在处理用户" + socket.getInetAddress().getHostName() + "将手机漫游到 " + str + "的请求");
            out.writeUTF("尊敬的用户，你的手机已经漫游到" + str + "了");
            Thread.sleep(1000);
            System.out.println("漫游处理完毕");
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MobileServer();
    }
}

