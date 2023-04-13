package com.huawei.softwarestructure.fan_ctrl;

/***********************************************************************
 * Module:  FanBrd.java
 * Author:  AARONFANG
 * Purpose: Defines the Class FanBrd
 ***********************************************************************/

import com.huawei.softwarestructure.Status;
import com.huawei.softwarestructure.fan_ctrl.drv.DrvContext;
import com.huawei.softwarestructure.fan_ctrl.drv.DrvType;
import com.huawei.softwarestructure.fan_ctrl.drv.drv_neptune.NeptuneInitStrategy;
import com.huawei.softwarestructure.fan_ctrl.drv.drv_normal.NormalInitStrategy;
import com.huawei.softwarestructure.srv_brd.ISrvBrd;
import com.huawei.softwarestructure.srv_brd.SrvBrdFactory;
import com.huawei.softwarestructure.srv_brd.SrvBrdListener;

import java.util.ArrayList;
import java.util.List;


public class FanBrdImpl implements IFanBrd, SrvBrdListener {

    private int slotId;

    private FanSpeed currFanSpeed;

    private FanBrdModeType currModeType;

    private final List<ISrvBrd> srvBrds = new ArrayList<>();

    FanBrdImpl(FanBrdConfig cfg) {
        this.slotId = cfg.getSlot();
        currFanSpeed = FanSpeed.FAN_SPEED_STOP;
        currModeType = FanBrdModeType.Automatic; // 默认自动挡
        executeConfig(cfg);
        System.out.println("[FanBrd] Brd:"+ slotId+", new brd created, "+toString());
    }

    /**
     * 根据风扇种类初始化风扇板信息
     * @param cfg 配置文件信息
     */
    private void executeConfig(FanBrdConfig cfg) {
        DrvContext drvContext = DrvContext.getInstance();
        if (cfg.getCommType() == DrvType.Neptune) {
            drvContext.setStrategy(new NeptuneInitStrategy());
        }
        else{
            drvContext.setStrategy(new NormalInitStrategy());
        }
        drvContext.executeStrategy();
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
        // todo
        if (temp > 70) {
            setCurrFanSpeed(FanSpeed.FAN_SPEED_HIGH);
        }
        else{
            setCurrFanSpeed(FanSpeed.FAN_SPEED_LOW);
        }
        return Status.getSuccessStatus("[FanBrdImpl] slot: "+ slot+" overheated， temperature is " + temp+ ", current fan speed: "+ currFanSpeed);
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

    public void setCurrFanSpeed(FanSpeed currFanSpeed) {
        this.currFanSpeed = currFanSpeed;
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
        return "FanBrd{" +
                "slotId=" + slotId +
                ", currFanSpeed=" + currFanSpeed +
                ", currModeType=" + currModeType +
                '}';
    }

}