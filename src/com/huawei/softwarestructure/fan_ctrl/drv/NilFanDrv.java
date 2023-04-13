package com.huawei.softwarestructure.fan_ctrl.drv;

import com.huawei.softwarestructure.Status;
import com.huawei.softwarestructure.fan_ctrl.FanSpeed;
import com.huawei.softwarestructure.fan_ctrl.drv.drv_neptune.NeptuneStatus;

/**
 * 空对象
 */
public class NilFanDrv implements IFanDrv{

    private int speed;
    private NeptuneStatus status;

    @Override
    public Status adjust(FanSpeed fanSpeed) {
        return Status.getSuccessStatus("");
    }

    @Override
    public boolean isError() {
        return status == NeptuneStatus.FAN_ERR;
    }
}
