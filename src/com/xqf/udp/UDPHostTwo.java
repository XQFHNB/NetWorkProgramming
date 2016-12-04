package com.xqf.udp;

/**
 * Created by XQF on 2016/12/4.
 */
public class UDPHostTwo {
    public UDPHostTwo(Model model) {
        new UDPCollection(model);
    }

    public static void main(String[] args) {
        Model model = new Model("主机2", 2016, 2012);
        new UDPHostTwo(model);
    }
}
