package com.huawei.softwarestructure;

public class Test {

    public static void main(String[] args) {
	    FanCtrlAction fanCtrlAction = new FanCtrlAction();
	    fanCtrlAction.initConfig(new FanBrdConfig(10000, "command 1"));
        fanCtrlAction.initConfig(new FanBrdConfig(10001, "command 2"));
        fanCtrlAction.initConfig(new FanBrdConfig(10002, "command 3"));

        fanCtrlAction.adjustFanSpeed(10000, FanSpeed.FAN_SPEED_HIGH);
        fanCtrlAction.adjustFanSpeed(10001, FanSpeed.FAN_SPEED_MID);

        fanCtrlAction.adjustFanSpeed(10004, FanSpeed.FAN_SPEED_MID);

        System.out.println("List of All Boards:");
        for (FanBrd brd : fanCtrlAction.getAvailableFanBrd()) {
            System.out.println(brd.toString());
        }
    }
}
