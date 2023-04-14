package com.huawei.softwarestructure;

import com.huawei.softwarestructure.ctrl.FanCtrlAction;
import com.huawei.softwarestructure.fan_ctrl.*;
import com.huawei.softwarestructure.fan_ctrl.drv.DrvType;

public class Test {

    public static void main(String[] args) {
	    FanCtrlAction fanCtrlAction = new FanCtrlAction(FanMgrImpl.getInstance());

        int fanBrd1Slot = 10000; // 风扇板1的槽位号
	    /* 风扇盒层次设置，使用组合模式
	     风扇板槽位为10000
	     --> 风扇盒10000-10, 驱动Mars
	     --> 风扇盒10000-11, 有两个子风扇盒
	         --> 10000-11-110, 驱动Mars
	         --> 10000-11-111, 驱动Mars
	     */
        FanBrdConfig.FanBoxNode fanBoxNode1 = new FanBrdConfig.NodeBuilder(fanBrd1Slot)
                .addChild(
                        new FanBrdConfig.NodeBuilder(10, DrvType.Mars)
                )
                .addChild(
                        new FanBrdConfig.NodeBuilder(11)
                                .addChild(new FanBrdConfig.NodeBuilder(110, DrvType.Mars))
                                .addChild(new FanBrdConfig.NodeBuilder(111, DrvType.Mars))
                )
                .build();

        fanCtrlAction.initFanBrdConfig(
                new FanBrdConfig.FanBrdConfigBuilder()
                        .addFanBrdSlot(fanBrd1Slot)
                        .addSrvBrd(20001)
                        .addSrvBrd(20002)
                        .initFanBoxesSetting(fanBoxNode1)
                        .build()
        ).showStatus();

        fanCtrlAction.initFanBrdConfig(
                new FanBrdConfig.FanBrdConfigBuilder()
                        .addFanBrdSlot(10001)
                        .addDrv(DrvType.Neptune)
                        .addSrvBrd(20003)
                        .addSrvBrd(20004)
                        .build()
        ).showStatus();

        fanCtrlAction.initFanBrdConfig(
                new FanBrdConfig.FanBrdConfigBuilder()
                        .addFanBrdSlot(10002)
                        .addDrv(DrvType.Venus)
                        .addSrvBrd(20005)
                        .addSrvBrd(20006)
                        .build()
        ).showStatus();

        // 默认为自动挡的情况下，此时进行的调速应该失败
        fanCtrlAction.manualAdjust(10000, FanSpeed.FAN_SPEED_HIGH).showStatus();
        fanCtrlAction.manualAdjust(10001, FanSpeed.FAN_SPEED_MID).showStatus();
        fanCtrlAction.manualAdjust(10004, FanSpeed.FAN_SPEED_MID).showStatus(); // 查询一块不存在的风扇板，失败

        System.out.println("List of All Boards:");
        for (IFanBrd brd : fanCtrlAction.getAvailableFanBrd()) {
            System.out.println(brd.toString());
        }

        // 第二次作业
        // 将slot为10000的风扇板的模式改为手动挡
        fanCtrlAction.configFanMode(10000, FanBrdModeType.Manual).showStatus();
        // 设置为手动挡之后调速
        fanCtrlAction.manualAdjust(10000, FanSpeed.FAN_SPEED_HIGH).showStatus();

        // 根据槽位号查找业务板，模拟采集到的温度先后为100和40
        fanCtrlAction.onSrvTempChanged(20003, 100);
        fanCtrlAction.onSrvTempChanged(20003, 40);
    }
}
