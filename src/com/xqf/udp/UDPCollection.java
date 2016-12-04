package com.xqf.udp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;

/**
 * Created by XQF on 2016/12/4.
 */
public class UDPCollection extends JFrame implements Runnable, ActionListener {
    private JTextField sendMsg;//信息内容文本框
    private JTextArea receivedMsg;//接收消息显示区
    private JButton sendBtn;
    private Container container;
    private Model model;


    private String titleString;
    private int inPort;
    private int outPort;

    public UDPCollection(Model model) {
        this.model = model;
        container = this.getContentPane();
        this.setSize(400, 300);
        this.setVisible(true);
        this.setTitle(model.getTitleString());
        container.setLayout(new BorderLayout());//给顶层容器的默认布局换成BorderLayout布局,不过顶层容器的默认布局就是这个呀，简直多此一举


// 添加滑动面板，也就是中间的消息界面
        JScrollPane centerPanel = new JScrollPane();//新建滑动面板对象
        receivedMsg = new JTextArea();
        centerPanel.setViewportView(receivedMsg);//把文本编辑区放进滚动面板
        // JScrollPanel centerPanel=new JScrollPanel(receivedMsg)
        container.add(centerPanel, BorderLayout.CENTER);//把滚动面板放在窗口最中间

// 添加底部面板，也就是编辑消息和发送消息按钮的面板
        JPanel bottomPanel = new JPanel();
        JLabel label = new JLabel("编辑信息");//创建一个标签提示栏对象
        sendMsg = new JTextField(20);//创建一个文本编辑框用于编辑消息，大小为20为列数，。大概也是指的宽度了。
        sendBtn = new JButton("发送消息");
        bottomPanel.add(label);
        bottomPanel.add(sendMsg);
        bottomPanel.add(sendBtn);
        container.add(bottomPanel, BorderLayout.SOUTH);

//下面对事件进行处理

        sendBtn.addActionListener(this);//注册点击按钮事件
        sendMsg.addActionListener(this);//注册聊天栏动作事件

// 新开一个线程用于接收数据
        Thread t = new Thread(this);//因为实现了runable接口
        t.start();
//点击退出就退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    //点击事件处理
    public void actionPerformed(ActionEvent event) {
        byte[] buffer = sendMsg.getText().trim().getBytes();
        InetAddress destAddress = null;//先拿到一个主机地址，创建一个InetAddress对象，这个对象在后续创建DatagramPacket有用
        try {
            destAddress = InetAddress.getByName("127.0.0.1");
        } catch (UnknownHostException e) {
            System.out.println("找不到主机！" + e.getMessage());
        }

        //构造数据包一直都在用这个方法，相对来说简单，把端口，大小什么的都说明了,
        //将buff.length()大小的数据buff发送到目的地址为destAddress的2012端口处
        DatagramPacket dataPacket = new DatagramPacket(buffer, buffer.length, destAddress, model.getOutPort());

        //创建套接字对象，准备打开管子放水了,这个类的构造方法还是比价简单，此时是发送就不需要绑定端口，所以使用这种构造方法
        DatagramSocket sendSocket = null;
        try {
            sendSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
            System.out.println("套接字创建错误！");
        }

//        receivedMsg.append("==================本地消息==================\n");
//        receivedMsg.append("数据报目标主机地址：" + dataPacket.getAddress() + "\n");//为了显摆一下在数据包里进行操作，InetAddress对象也可以获得。
//        receivedMsg.append("数据报目标端口：" + dataPacket.getPort() + "\n");
//        receivedMsg.append("数据报长度：" + dataPacket.getLength() + "\n");

        receivedMsg.append(model.getTitleString()+"发出："+sendMsg.getText().trim()+"\n");
        //发送数据报
        try {
            sendSocket.send(dataPacket);
        } catch (IOException e) {
            System.out.println("数据报发送错误！");
        }


        //将消息编辑界面置空，看上去是不见了
        sendMsg.setText("");
    }


    //子线程要干的事情,接收数据
    public void run() {
        DatagramPacket receivedPacket = null;
        DatagramSocket receivedSocket = null;
        byte[] buffer = new byte[8192];

        try {

            //发送的时候使用四个参数的构造方法，接收的时候使用两个参数的构造方法
            receivedPacket = new DatagramPacket(buffer, buffer.length);
            //接收的Socket与端口绑定，就是为了接收这个端口的数据
            receivedSocket = new DatagramSocket(model.getInPort());
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while (true) {
            //如果套接字为空就跳出死循环,这里很奇怪，既然套接字里面都不为空了为什么在后面才来获取数据包
            if (receivedSocket == null) {
                break;
            } else {
                try {
                    receivedSocket.receive(receivedPacket);//接收数据，这句过后receivedPacket就不是空的了
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int length = receivedPacket.getLength();//获取内容长度只能在数据包里获取，不能在套接字，。，。套接字只是一条路。
                InetAddress address = receivedPacket.getAddress();//拿到此套接字连接的地址，即获取发送人的地址
                int port = receivedSocket.getPort();//获取发送者发送数据的端口号

                //将获取到的数据转换为字符串
                String message = new String(receivedPacket.getData(), 0, length);//查了一下API文档，里面的String真的有这个构造方法
//                receivedMsg.append("==================异地消息==================");
//                receivedMsg.append("收到数据长度：" + length + "\n");
//                receivedMsg.append("收到数据来自：" + address + " 端口：" + port + "\n");
//                receivedMsg.append("收到数据是：" + message + "\n");
                receivedMsg.append(model.getTitleString() + "收到：" + message+"\n");
            }
        }
    }
}
