package com.huawei.softwarestructure.fan_ctrl;

import java.util.List;

/***********************************************************************
 * Module:  FanBrdConfig.java
 * Author:  AARONFANG
 * Purpose: Defines the Class FanBrdConfig
 ***********************************************************************/

public class FanBrdConfig {

    public FanBrdConfig(int slotNum, String commType,  List<Integer> srvBrdSlotList){
        this.slot = slotNum;
        this.commType = commType;
        this.srvBrdSlotList = srvBrdSlotList;
    }

    private int slot;

    private String commType;

    private List<Integer> srvBrdSlotList;

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

    public List<Integer> getSrvBrdSlotList() {
        return srvBrdSlotList;
    }

    public void setSrvBrdSlotList(List<Integer> srvBrdSlotList) {
        this.srvBrdSlotList = srvBrdSlotList;
    }
}