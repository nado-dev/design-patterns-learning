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
        return new FanBrdImpl(cfg);
    }
}