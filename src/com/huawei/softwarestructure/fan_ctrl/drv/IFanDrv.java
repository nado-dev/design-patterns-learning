package com.huawei.softwarestructure.fan_ctrl.drv;

import com.huawei.softwarestructure.Status;
import com.huawei.softwarestructure.fan_ctrl.FanSpeed;

public interface IFanDrv {
    Status adjust(FanSpeed fanSpeed);
    boolean isError();
}
