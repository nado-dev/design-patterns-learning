package com.nadodev.softwarestructure.fan_ctrl.drv.drv_normal;

import com.nadodev.softwarestructure.Status;
import com.nadodev.softwarestructure.fan_ctrl.FanSpeed;
import com.nadodev.softwarestructure.fan_ctrl.drv.IFanDrv;

public class VenusFanDrv implements IFanDrv {

    private FanSpeed speed;
    private boolean status;

    @Override
    public Status adjust(FanSpeed fanSpeed) {
        this.speed = fanSpeed;
        return Status.getSuccessStatus("[VenusFanDrv] new speed set, "+toString());
    }

    @Override
    public boolean isError() {
        return status;
    }

    public FanSpeed getSpeed() {
        return speed;
    }

    public void setSpeed(FanSpeed speed) {
        this.speed = speed;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "VenusFanDrv{" +
                "speed=" + speed +
                ", status=" + status +
                '}';
    }
}
