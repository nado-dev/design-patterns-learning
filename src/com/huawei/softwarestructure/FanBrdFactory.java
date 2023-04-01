package com.huawei.softwarestructure;

/***********************************************************************
 * Module:  FanBrdFactory.java
 * Author:  AARONFANG
 * Purpose: Defines the Class FanBrdFactory
 ***********************************************************************/

import java.util.*;


public class FanBrdFactory {

    private FanBrdFactory() {

    }


    public static FanBrd makeFanBrd(FanBrdConfig cfg) {
        return new FanBrd(cfg.getSlot());
    }
}