package com.huawei.softwarestructure.ctrl;

/***********************************************************************
 * Module:  FanCtrlAction.java
 * Author:  AARONFANG
 * Purpose: Defines the Class FanCtrlAction
 ***********************************************************************/

import com.huawei.softwarestructure.*;
import com.huawei.softwarestructure.fan_ctrl.*;
import com.huawei.softwarestructure.srv_brd.ISrvBrd;

import java.util.*;

public class FanCtrlAction {

    private final IFanMgr fanMgr;

    public FanCtrlAction(IFanMgr fanMgr) {
        this.fanMgr = fanMgr;
    }

    public Status initFanBrdConfig(FanBrdConfig cfg) {
        return fanMgr.initConfig(cfg);
    }

    public Status manualAdjust(int slot, FanSpeed speed) {
        IFanBrd brd = fanMgr.getFanBrdBySlotId(slot);
        if (brd == null) {
            return new Status(false, "[FanCtrlAction] no brd found: "+slot);
        }
        return brd.manualAdjust(speed);
    }

    public List<IFanBrd> getAvailableFanBrd() {
        return fanMgr.getFanBrdList();
    }

    public Status configFanMode(int slot, FanBrdModeType modeType) {
        IFanBrd brd = fanMgr.getFanBrdBySlotId(slot);
        if (brd == null) {
            return new Status(false, "[FanCtrlAction] no brd found: "+slot);
        }
        return brd.setFanMode(modeType);
    }

    public Status onSrvTempChanged(int slot, int temp) {
        ISrvBrd srvBrd = getSrvBrdBySlotId(slot);
        // 模拟业务板温度变化
        if (srvBrd != null) {
            return srvBrd.onTempChange(temp);
        }
        return Status.getFailStatus("onSrvTempChanged failed");
    }

    private ISrvBrd getSrvBrdBySlotId(int slot) {
        List<IFanBrd> fanBrd = getAvailableFanBrd();
        for (IFanBrd iFanBrd : fanBrd) {
            ISrvBrd srvBrd =  iFanBrd.findSrvBrd(slot);
            if (srvBrd != null)
                return srvBrd;
        }
        System.out.println("no such SrvBrd");
        return null;
    }
}