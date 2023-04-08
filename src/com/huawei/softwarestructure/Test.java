package com.huawei.softwarestructure;

import com.huawei.softwarestructure.ctrl.FanCtrlAction;
import com.huawei.softwarestructure.fan_ctrl.*;

public class Test {

    public static void main(String[] args) {
        // 第一次作业
	    FanCtrlAction fanCtrlAction = new FanCtrlAction(FanMgrImpl.getInstance());
	    fanCtrlAction.initFanBrdConfig(new FanBrdConfig(10000, "type1")).showStatus();
        fanCtrlAction.initFanBrdConfig(new FanBrdConfig(10001, "type1")).showStatus();
        fanCtrlAction.initFanBrdConfig(new FanBrdConfig(10002, "type1")).showStatus();

        fanCtrlAction.manualAdjust(10000, FanSpeed.FAN_SPEED_HIGH).showStatus();
        fanCtrlAction.manualAdjust(10001, FanSpeed.FAN_SPEED_MID).showStatus();
        fanCtrlAction.manualAdjust(10004, FanSpeed.FAN_SPEED_MID).showStatus();

        System.out.println("List of All Boards:");
        for (IFanBrd brd : fanCtrlAction.getAvailableFanBrd()) {
            System.out.println(brd.toString());
        }

        // 第二次作业
        fanCtrlAction.configFanMode(10000, FanBrdModeType.Manual).showStatus();
        fanCtrlAction.manualAdjust(10000, FanSpeed.FAN_SPEED_HIGH).showStatus();
    }
}
