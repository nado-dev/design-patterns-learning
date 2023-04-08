package com.huawei.softwarestructure.fan_ctrl;

/***********************************************************************
 * Module:  FanBrdFactory.java
 * Author:  AARONFANG
 * Purpose: Defines the Class FanBrdFactory
 ***********************************************************************/


public class FanBrdFactory {

    private FanBrdFactory() {

    }


    public static IFanBrd makeFanBrd(FanBrdConfig cfg) {
        if (cfg.getCommType().equals("type1")) {
            return new FanBrdImpl(cfg.getSlot());
        }
        return null;
    }
}