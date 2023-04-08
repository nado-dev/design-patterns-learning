package com.huawei.softwarestructure.fan_ctrl;

/***********************************************************************
 * Module:  FanBrd.java
 * Author:  AARONFANG
 * Purpose: Defines the Class FanBrd
 ***********************************************************************/

import com.huawei.softwarestructure.Status;
import com.huawei.softwarestructure.srv_brd.ISrvBrd;
import com.huawei.softwarestructure.srv_brd.SrvBrdListener;

import java.util.List;


public class FanBrdImpl implements IFanBrd, SrvBrdListener {

    private int slotId;

    private FanSpeed currFanSpeed;

    private FanBrdModeType currModeType;

    private List<ISrvBrd> srvBrds;

    FanBrdImpl(int slotId) {
        this.slotId = slotId;
        currFanSpeed = FanSpeed.FAN_SPEED_STOP;
        currModeType = FanBrdModeType.Automatic; // 默认自动挡
        System.out.println("[FanBrd] Brd:"+ slotId+", new brd created, "+toString());
    }

    public boolean isMatch(int slotId) {
        return this.slotId == slotId;
    }

    @Override
    public ISrvBrd findSrvBrd(int slot) {
        for (ISrvBrd brd : srvBrds) {
            if (brd.getBrdId() == slot) return brd;
        }
        return null;
    }

    @Override
    public Status onSrvHot(int slot, int temp) {
        // 手动模式下不会根据业务板温度执行任何操作
        System.out.println("no more action");
        return null;
    }

    @Override
    public Status manualAdjust(FanSpeed fs) {
        // 自动模式下手工配置风扇板档位需报错
        if (currModeType == FanBrdModeType.Automatic) {
            return Status.getFailStatus("[FanBrd] Brd: "+slotId+", 自动模式下禁止手工配置风扇板档位");
        }
        setCurrFanSpeed(fs);
        return Status.getSuccessStatus("[FanBrd] Brd:"+ slotId+", new Speed Set:" + fs);
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

    public Status onConfigSrvBrd(FanBrdModeType modeType) {
        this.currModeType = modeType;
        return Status.getSuccessStatus("[FanBrd] Brd: "+slotId+" 模式配置成功, "+ toString());
    }

    @Override
    public String toString() {
        return "FanBrd{" +
                "slotId=" + slotId +
                ", currFanSpeed=" + currFanSpeed +
                ", currModeType=" + currModeType +
                '}';
    }

    @Override
    public Status onSrcHot(int slot, int temp) {
        return null;
    }
}