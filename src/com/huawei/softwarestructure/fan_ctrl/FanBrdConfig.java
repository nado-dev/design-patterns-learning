package com.huawei.softwarestructure.fan_ctrl;

/***********************************************************************
 * Module:  FanBrdConfig.java
 * Author:  AARONFANG
 * Purpose: Defines the Class FanBrdConfig
 ***********************************************************************/

public class FanBrdConfig {

    public FanBrdConfig(int slotNum, String commType){
        this.slot = slotNum;
        this.commType = commType;
    }

    private int slot;

    private String commType;

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }

    public String getCommType() {
        return commType;
    }

    public void setCommType(String commType) {
        this.commType = commType;
    }
}