package com.xqf.tcp;

/**
 * Created by XQF on 2016/12/3.
 */
public interface BusinessProtocal {
    public static final int PAY_BILL = 1;
    public static final int ROAMING_SERVICE = 2;

    public void paybill();

    public void roamingService();
}
