package com.huawei.softwarestructure.fan_ctrl.fan_box;

import com.huawei.softwarestructure.Status;
import com.huawei.softwarestructure.fan_ctrl.FanSpeed;
import com.huawei.softwarestructure.fan_ctrl.drv.IFanDrv;

/**
 * 组合模式：具体风扇盒（叶子结点），执行具体的操作
 */
public class ConcreteFanBox implements IFanBox{

    private IFanDrv drv;

    @Override
    public Status adjustFanSpeed(FanSpeed fanSpeed) {
        return drv.adjust(fanSpeed);
    }
}
