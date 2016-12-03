package com.xqf.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by XQF on 2016/12/3.
 */
public class MobileClient  implements BusinessProtocal{
    private String string ;
    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;
    public MobileClient(){
        try {
            socket=new Socket("192.168.177.2",2010);
            in=new DataInputStream(socket.getInputStream());
            out=new DataOutputStream(socket.getOutputStream());
            paybill();
            Thread.sleep(500);//假装很耗时
            roamingService();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void paybill() {
        try {
            out.writeInt(PAY_BILL);
            out.writeInt(200);
            out.flush();//


            //得到的返回 
            string=in.readUTF();
            System.out.println("来自服务员："+string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void roamingService() {
        try {
            out.writeInt(ROAMING_SERVICE);
            out.writeUTF("香港");
            out.flush();
// 得到的返回
            string=in.readUTF();
            System.out.println("来自服务员："+string);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new MobileClient();
    }
}
