package com.xqf.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by XQF on 2016/12/3.
 */
public class URLConnectionTest {
    //    public static final String URL_STRING = "http://www.baidu.com/index.html";
    public static final String URL_STRING = "http://www.52duzhe.com/2016_07/index.html";

    public static void main(String[] args) {
        URL url = null;
        try {
            url = new URL(URL_STRING);
            URLConnection connection = url.openConnection();
            System.out.println("文件类型：" + connection.getContentType());
            System.out.println("文件长度：" + connection.getContentLength());
            System.out.println("文件内容：" + connection.getContent());
            System.out.println("-----------------------------------------------------");
            ;
            String string;
            StringBuffer sb = new StringBuffer();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((string = br.readLine()) != null) {
                sb.append(string);
            }
            System.out.println(sb.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
