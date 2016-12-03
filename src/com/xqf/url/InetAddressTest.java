package com.xqf.url;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by XQF on 2016/12/3.
 */
public class InetAddressTest {
    public static void main(String[] args) {
        try {
            //获取给定域名的地址
            InetAddress inetAddress1 = null;
            inetAddress1 = InetAddress.getByName("www.baidu.com");
            System.out.println("-----------------------------------------------------");

            System.out.println(inetAddress1.getHostName());//显示主机名
            System.out.println(inetAddress1.getHostAddress());//显示IP地址
            System.out.println(inetAddress1);//显示地址的字符串描述
            System.out.println("-----------------------------------------------------");

            //获取本机的地址
            InetAddress inetAddress2 = InetAddress.getLocalHost();
            System.out.println(inetAddress2.getHostName());
            System.out.println(inetAddress2.getHostAddress());
            System.out.println(inetAddress2);
            System.out.println("-----------------------------------------------------");

            //获取给定IP的主机的地址（72.5.124.55
            byte[] bytes = new byte[]{(byte) 72, (byte) 5, (byte) 124, (byte) 55};
            InetAddress inetAddress3 = InetAddress.getByAddress(bytes);
            InetAddress inetAddress4 = InetAddress.getByAddress("Sun 官方网站 21(java.sun.com)", bytes);
            System.out.println(inetAddress3);
            System.out.println(inetAddress4);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


    }
}
