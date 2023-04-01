package com.huawei.softwarestructure;

/***********************************************************************
 * Module:  FanCtrlAction.java
 * Author:  AARONFANG
 * Purpose: Defines the Class FanCtrlAction
 ***********************************************************************/

import java.util.*;

public class FanCtrlAction {

    private final FanMgr fanMgr;

    FanCtrlAction() {
        fanMgr = FanMgr.getInstance();
    }

    public Status initConfig(FanBrdConfig cfg) {
        return fanMgr.initConfig(cfg);
    }

    public Status adjustFanSpeed(int slot, FanSpeed speed) {
        FanBrd brd = fanMgr.getFanBrdBySlotId(slot);
        if (brd == null) {
            System.out.println("[FanCtrlAction] no brd found: "+slot);
            return new Status(false, "no brd found");
        }
        return brd.setFanSpeed(speed);
    }

    public List<FanBrd> getAvailableFanBrd() {
        return fanMgr.getFanBrdList();
    }
}