package com.huawei.softwarestructure.fan_ctrl;

import com.huawei.softwarestructure.Status;

public interface IFanBrd {

    Status manualAdjust(FanSpeed fs);
    boolean isMatch(int slotId);
}
