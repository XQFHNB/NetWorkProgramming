package com.xqf.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by XQF on 2016/12/3.
 */
public class URLTest {
    public static final String URL_STRING="http://www.baidu.com/index.html";
    public static void main(String[] args) throws IOException {
        URL url= null;
        try {
            url = new URL(URL_STRING);
            System.out.println("协议：" + url.getProtocol());
            System.out.println("主机：" + url.getHost());
            System.out.println("端口：" + url.getPort());
            System.out.println("路径：" + url.getPath());
            System.out.println("文件：" + url.getFile());
            String string;
            StringBuffer sb = new StringBuffer();
            InputStreamReader is=new InputStreamReader(url.openStream());
            BufferedReader br=new BufferedReader(is);
            while((string=br.readLine())!=null){
                sb.append(string);
            }
            System.out.println(sb.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
