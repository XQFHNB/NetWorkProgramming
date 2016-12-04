package com.xqf.udp;

/**
 * Created by XQF on 2016/12/3.
 */
public class UDPHostOne {
    public UDPHostOne(Model model) {
        new UDPCollection(model);
    }

    public static void main(String[] args) {
        Model model = new Model("主机1", 2012, 2016);
        new UDPHostOne(model);
    }
}
