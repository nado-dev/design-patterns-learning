package com.huawei.softwarestructure.fan_ctrl;

/***********************************************************************
 * Module:  FanBrd.java
 * Author:  AARONFANG
 * Purpose: Defines the Class FanBrd
 ***********************************************************************/

import com.huawei.softwarestructure.Status;


public class IFanBrdImpl implements IFanBrd {

    private int slotId;

    private FanSpeed currFanSpeed;

    IFanBrdImpl(int slotId) {
        this.slotId = slotId;
        currFanSpeed = FanSpeed.FAN_SPEED_STOP;
        System.out.println("[FanBrd] Brd:"+ slotId+", new brd created");
    }

    public boolean isMatch(int slotId) {
        return this.slotId == slotId;
    }

    public Status manualAdjust(FanSpeed fs) {
        setCurrFanSpeed(fs);
        System.out.println("[FanBrd] Brd:"+ slotId+", new Speed Set:" + fs);
        return null;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public FanSpeed getCurrFanSpeed() {
        return currFanSpeed;
    }

    public void setCurrFanSpeed(FanSpeed currFanSpeed) {
        this.currFanSpeed = currFanSpeed;
    }

    @Override
    public String toString() {
        return "FanBrd{" +
                "slotId=" + slotId +
                ", currFanSpeed=" + currFanSpeed +
                '}';
    }
}