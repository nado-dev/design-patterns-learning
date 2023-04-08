package com.huawei.softwarestructure;

import com.huawei.softwarestructure.ctrl.FanCtrlAction;
import com.huawei.softwarestructure.fan_ctrl.FanMgrImpl;
import com.huawei.softwarestructure.fan_ctrl.IFanBrd;
import com.huawei.softwarestructure.fan_ctrl.FanBrdConfig;
import com.huawei.softwarestructure.fan_ctrl.FanSpeed;

public class Test {

    public static void main(String[] args) {
	    FanCtrlAction fanCtrlAction = new FanCtrlAction(FanMgrImpl.getInstance());
	    fanCtrlAction.initConfig(new FanBrdConfig(10000, "type1"));
        fanCtrlAction.initConfig(new FanBrdConfig(10001, "type1"));
        fanCtrlAction.initConfig(new FanBrdConfig(10002, "type1"));

        fanCtrlAction.adjustFanSpeed(10000, FanSpeed.FAN_SPEED_HIGH);
        fanCtrlAction.adjustFanSpeed(10001, FanSpeed.FAN_SPEED_MID);

        fanCtrlAction.adjustFanSpeed(10004, FanSpeed.FAN_SPEED_MID);

        System.out.println("List of All Boards:");
        for (IFanBrd brd : fanCtrlAction.getAvailableFanBrd()) {
            System.out.println(brd.toString());
        }
    }
}
