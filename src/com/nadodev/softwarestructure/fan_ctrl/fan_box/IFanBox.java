package com.nadodev.softwarestructure.fan_ctrl.fan_box;

import com.nadodev.softwarestructure.Status;
import com.nadodev.softwarestructure.fan_ctrl.FanSpeed;

public interface IFanBox {
    /**
     * 具体行为：调速
     * @return 状态
     */
    Status adjustFanSpeed(FanSpeed fanSpeed);
}
