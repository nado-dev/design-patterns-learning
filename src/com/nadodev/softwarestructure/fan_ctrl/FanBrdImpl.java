package com.nadodev.softwarestructure.fan_ctrl;

/***********************************************************************
 * Module:  FanBrd.java
 * Author:  AARONFANG
 * Purpose: Defines the Class FanBrd
 ***********************************************************************/

import com.nadodev.softwarestructure.Status;
import com.nadodev.softwarestructure.fan_ctrl.drv.IFanDrv;
import com.nadodev.softwarestructure.fan_ctrl.drv.NilFanDrv;
import com.nadodev.softwarestructure.fan_ctrl.fan_box.FanBoxUtils;
import com.nadodev.softwarestructure.fan_ctrl.fan_box.IFanBox;
import com.nadodev.softwarestructure.srv_brd.ISrvBrd;
import com.nadodev.softwarestructure.srv_brd.SrvBrdFactory;
import com.nadodev.softwarestructure.srv_brd.SrvBrdListener;

import java.util.ArrayList;
import java.util.List;


public class FanBrdImpl implements IFanBrd, SrvBrdListener {

    /**
     * 风扇板槽位
     */
    private int slotId;

    /**
     * 当前转速
     */
    private FanSpeed currFanSpeed;

    /**
     * 当前模式：手动/自动
     */
    private FanBrdModeType currModeType;

    /**
     * 风扇板统一驱动（老版本）
     */
    private IFanDrv fanDrv;

    /**
     * 所有管理的业务板
     */
    private final List<ISrvBrd> srvBrds = new ArrayList<>();

    /**
     * 风扇盒的抽象，有统一的调速接口
     */
    private IFanBox iFanBox;

    FanBrdImpl(FanBrdConfig cfg) {
        this.slotId = cfg.getSlot();
        currModeType = FanBrdModeType.Automatic; // 默认自动挡
        executeConfig(cfg);
        setCurrFanSpeed(FanSpeed.FAN_SPEED_STOP);
    }

    /**
     * 根据风扇种类初始化风扇板信息
     * @param cfg 配置文件信息
     */
    private void executeConfig(FanBrdConfig cfg) {
        this.fanDrv = FanBoxUtils.getFanDrvByType(cfg.getCommType());
        iFanBox = FanBoxUtils.copyFrom(cfg.getFanBoxNode());
        FanBoxUtils.executeStrategy(cfg.getCommType());
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
        if (currModeType == FanBrdModeType.Manual) {
            return Status.getFailStatus("[FanBrd] Brd: "+slotId+", 手动模式下不会根据业务板温度执行任何操作");
        }
        if (temp > 70) {
            setCurrFanSpeed(FanSpeed.FAN_SPEED_HIGH);
            System.out.println("[FanBrdImpl] slot: "+ slot+" overheated， temperature is " + temp+ ", current fan speed: "+ currFanSpeed);
        }
        else{
            setCurrFanSpeed(FanSpeed.FAN_SPEED_LOW);
            System.out.println("[FanBrdImpl] slot: "+ slot+" cool down， temperature is " + temp+ ", current fan speed: "+ currFanSpeed);
        }
        return Status.getSuccessStatus("");
    }

    @Override
    public Status manualAdjust(FanSpeed fs) {
        // 自动模式下手工配置风扇板档位需报错
        if (currModeType == FanBrdModeType.Automatic) {
            return Status.getFailStatus("[FanBrd] Brd:"+slotId+", 自动模式下禁止手工配置风扇板档位");
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

    public void setCurrFanSpeed(FanSpeed newFanSpeed) {
        if (fanDrv instanceof NilFanDrv) return;
        Status ret = iFanBox.adjustFanSpeed(newFanSpeed);// 新风扇板
        fanDrv.adjust(newFanSpeed); // 老风扇板
        if (ret.isSuccess()) this.currFanSpeed = newFanSpeed;
    }

    public Status setFanMode(FanBrdModeType modeType) {
        this.currModeType = modeType;
        return Status.getSuccessStatus("[FanBrd] Brd: "+slotId+" 模式配置成功, "+ toString());
    }

    @Override
    public Status configSrvBrds(FanBrdConfig config) {
        for (Integer slot : config.getSrvBrdSlotList()) {
            ISrvBrd srvBrd = SrvBrdFactory.makeSrvBrd(slot);
            srvBrds.add(srvBrd);
            // 订阅温度变化事件
            srvBrd.addListener(this);
        }
        return Status.getSuccessStatus("业务板创建成功" + srvBrds.toString());
    }

    @Override
    public String toString() {
        return "FanBrdImpl{" +
                "slotId=" + slotId +
                ", currFanSpeed=" + currFanSpeed +
                ", currModeType=" + currModeType +
                ", fanDrv=" + fanDrv +
                ", srvBrds=" + srvBrds +
                ", iFanBox=" + iFanBox +
                '}';
    }
}