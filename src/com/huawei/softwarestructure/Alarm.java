package com.huawei.softwarestructure;

import com.huawei.softwarestructure.fan_ctrl.FanMgrImpl;
import com.huawei.softwarestructure.srv_brd.SrvBrdListener;

public class Alarm implements SrvBrdListener {

    private Alarm() {}

    private static Alarm instance;

    public static Alarm getInstance() {
        if (instance == null) {
            synchronized (FanMgrImpl.class) {
                if (instance == null) {
                    instance = new Alarm();
                }
            }
        }
        return instance;
    }

    @Override
    public Status onSrvHot(int slot, int temp) {
        System.out.println("[Alarm] slot: "+ slot+" overheated， temperature is " + temp);
        return Status.getSuccessStatus("[Alarm] slot: "+ slot+" overheated， temperature is " + temp);
    }
}
