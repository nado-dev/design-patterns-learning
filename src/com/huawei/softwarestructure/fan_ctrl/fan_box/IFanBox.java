package com.huawei.softwarestructure.fan_ctrl.fan_box;

import com.huawei.softwarestructure.Status;
import com.huawei.softwarestructure.fan_ctrl.FanSpeed;

public interface IFanBox {
    /**
     * 具体行为：调速
     * @return 状态
     */
    Status adjustFanSpeed(FanSpeed fanSpeed);
}
