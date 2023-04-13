package com.huawei.softwarestructure.fan_ctrl.drv.drv_neptune;

import com.huawei.softwarestructure.Status;
import com.huawei.softwarestructure.fan_ctrl.FanSpeed;
import com.huawei.softwarestructure.fan_ctrl.drv.IFanDrv;

public class NeptuneFanDrvAdapter implements IFanDrv {
    private final NeptuneFanDrv neptuneFanDrv;

    public NeptuneFanDrvAdapter(NeptuneFanDrv neptuneFanDrv) {
        this.neptuneFanDrv = neptuneFanDrv;
    }

    @Override
    public Status adjust(FanSpeed fanSpeed) {
        neptuneFanDrv.setSpeed(fanSpeed.getSpeed());
        return Status.getSuccessStatus("[NeptuneFanDrvAdapter] speed set, "+neptuneFanDrv.toString());
    }

    @Override
    public boolean isError() {
        return neptuneFanDrv.getStatus() == NeptuneStatus.FAM_OK;
    }
}
