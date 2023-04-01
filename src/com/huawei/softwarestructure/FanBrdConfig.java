package com.huawei.softwarestructure;

/***********************************************************************
 * Module:  FanBrdConfig.java
 * Author:  AARONFANG
 * Purpose: Defines the Class FanBrdConfig
 ***********************************************************************/

public class FanBrdConfig {

    FanBrdConfig(int slotNum, String commType){
        this.slot = slotNum;
        this.commType = commType;
    }

    private int slot;

    private String commType;

    public int getSlot() {
        return slot;
    }
}