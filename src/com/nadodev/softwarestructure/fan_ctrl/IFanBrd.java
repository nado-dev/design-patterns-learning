package com.nadodev.softwarestructure.fan_ctrl;

import com.nadodev.softwarestructure.Status;
import com.nadodev.softwarestructure.srv_brd.ISrvBrd;

public interface IFanBrd {

    Status manualAdjust(FanSpeed fs);
    boolean isMatch(int slotId);
    ISrvBrd findSrvBrd(int slot);
    Status setFanMode(FanBrdModeType modeType);
    Status configSrvBrds(FanBrdConfig config);
}
