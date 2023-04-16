package com.nadodev.softwarestructure.fan_ctrl.drv.drv_neptune;

public class NeptuneFanDrv {

    private int speed;
    private NeptuneStatus status;

    public NeptuneStatus getStatus() {
        return status;
    }

    public void setStatus(NeptuneStatus status) {
        this.status = status;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "NeptuneFanDrv{" +
                "speed=" + speed +
                ", status=" + status +
                '}';
    }
}
