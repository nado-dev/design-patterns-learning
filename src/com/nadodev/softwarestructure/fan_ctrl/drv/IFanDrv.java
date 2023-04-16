package com.nadodev.softwarestructure.fan_ctrl.drv;

import com.nadodev.softwarestructure.Status;
import com.nadodev.softwarestructure.fan_ctrl.FanSpeed;

public interface IFanDrv {
    Status adjust(FanSpeed fanSpeed);
    boolean isError();
}
