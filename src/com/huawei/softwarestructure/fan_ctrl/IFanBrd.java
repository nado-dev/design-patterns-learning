package com.huawei.softwarestructure.fan_ctrl;

import com.huawei.softwarestructure.Status;
import com.huawei.softwarestructure.srv_brd.ISrvBrd;

public interface IFanBrd {

    Status manualAdjust(FanSpeed fs);
    boolean isMatch(int slotId);
    ISrvBrd findSrvBrd(int slot);
    Status onSrvHot(int slot, int temp);
    Status onConfigSrvBrd(FanBrdModeType modeType);
}
