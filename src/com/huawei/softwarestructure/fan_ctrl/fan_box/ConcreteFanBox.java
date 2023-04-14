package com.huawei.softwarestructure.fan_ctrl.fan_box;

import com.huawei.softwarestructure.Status;
import com.huawei.softwarestructure.fan_ctrl.FanSpeed;
import com.huawei.softwarestructure.fan_ctrl.drv.DrvType;
import com.huawei.softwarestructure.fan_ctrl.drv.IFanDrv;
import com.huawei.softwarestructure.fan_ctrl.drv.NilFanDrv;

/**
 * 组合模式：具体风扇盒（叶子结点），执行具体的操作
 */
public class ConcreteFanBox implements IFanBox{

    private IFanDrv fanDrv;
    private int slot;
    private DrvType drvType;

    public ConcreteFanBox(int slot, DrvType drvType) {
        this.slot = slot;
        this.drvType = drvType;
        init();
    }

    private void init() {
        this.fanDrv = FanBoxUtils.getFanDrvByType(drvType);
        FanBoxUtils.executeStrategy(drvType);
    }


    @Override
    public Status adjustFanSpeed(FanSpeed fanSpeed) {
        if (fanDrv instanceof NilFanDrv)
            return Status.getFailStatus("[ConcreteFanBox] slot "+ slot+" Cannot Use NilDriver");

        System.out.println("[ConcreteFanBox] slot "+ slot+" New Speed set, "+ fanSpeed.toString());
        return fanDrv.adjust(fanSpeed);
    }

    @Override
    public String toString() {
        return "ConcreteFanBox{" +
                "fanDrv=" + fanDrv +
                ", slot=" + slot +
                ", drvType=" + drvType +
                '}';
    }
}
