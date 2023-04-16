package com.nadodev.softwarestructure.fan_ctrl.drv.drv_normal;

import com.nadodev.softwarestructure.Status;
import com.nadodev.softwarestructure.fan_ctrl.FanSpeed;
import com.nadodev.softwarestructure.fan_ctrl.drv.IFanDrv;

public class MarsFanDrv implements IFanDrv {

    private FanSpeed speed;
    private boolean status;

    @Override
    public Status adjust(FanSpeed fanSpeed) {
        this.speed = fanSpeed;
        return Status.getSuccessStatus("[MarsFanDrv] new speed set, "+toString());
    }

    @Override
    public boolean isError() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public FanSpeed getSpeed() {
        return speed;
    }

    public void setSpeed(FanSpeed speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "MarsFanDrv{" +
                "speed=" + speed +
                ", status=" + status +
                '}';
    }
}
