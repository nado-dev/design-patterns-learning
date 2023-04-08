package com.huawei.softwarestructure.ctrl;

/***********************************************************************
 * Module:  FanCtrlAction.java
 * Author:  AARONFANG
 * Purpose: Defines the Class FanCtrlAction
 ***********************************************************************/

import com.huawei.softwarestructure.*;
import com.huawei.softwarestructure.fan_ctrl.*;

import java.util.*;

public class FanCtrlAction {

    private final IFanMgr fanMgr;

    public FanCtrlAction(IFanMgr fanMgr) {
        this.fanMgr = fanMgr;
    }

    public Status initConfig(FanBrdConfig cfg) {
        return fanMgr.initConfig(cfg);
    }

    public Status adjustFanSpeed(int slot, FanSpeed speed) {
        IFanBrd brd = fanMgr.getFanBrdBySlotId(slot);
        if (brd == null) {
            System.out.println("[FanCtrlAction] no brd found: "+slot);
            return new Status(false, "no brd found");
        }
        return brd.manualAdjust(speed);
    }

    public List<IFanBrd> getAvailableFanBrd() {
        return fanMgr.getFanBrdList();
    }
}