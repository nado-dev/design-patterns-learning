package com.nadodev.softwarestructure.fan_ctrl;

public enum FanSpeed {
    FAN_SPEED_STOP(0),
    FAN_SPEED_LOW(10),
    FAN_SPEED_MID_LOW(20),
    FAN_SPEED_MID(30),
    FAN_SPEED_MID_HIGH(40),
    FAN_SPEED_HIGH(50);

    private final int speed;
    FanSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}
