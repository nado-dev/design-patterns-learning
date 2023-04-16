package com.nadodev.softwarestructure;

import com.nadodev.softwarestructure.ctrl.FanCtrlAction;
import com.nadodev.softwarestructure.fan_ctrl.*;
import com.nadodev.softwarestructure.fan_ctrl.drv.DrvType;

public class Test {

    public static void main(String[] args) {
	    FanCtrlAction fanCtrlAction = new FanCtrlAction(FanMgrImpl.getInstance());

        int fanBrd1Slot = 10000; // 风扇板1的槽位号

	    /* 风扇盒层次设置，使用组合模式
	     * 风扇板槽位为10000
	     * --> 风扇盒10000-10, 驱动Mars
	     * --> 风扇盒10000-11, 有两个子风扇盒
	     *    --> 10000-11-110, 驱动Mars
         *    --> 10000-11-111, 驱动Mars
	     */
        FanBrdConfig.FanBoxNode fanBoxNode1 = new FanBrdConfig.NodeBuilder(fanBrd1Slot)
                .addChild(
                        new FanBrdConfig.NodeBuilder(10, DrvType.Mars)
                )
                .addChild(
                        new FanBrdConfig.NodeBuilder(11)
                                .addChild(new FanBrdConfig.NodeBuilder(110, DrvType.Neptune))
                                .addChild(new FanBrdConfig.NodeBuilder(111, DrvType.Mars))
                )
                .build();

        // 初始化风扇板配置
        fanCtrlAction.initFanBrdConfig(
                new FanBrdConfig.FanBrdConfigBuilder()
                        .addFanBrdSlot(fanBrd1Slot)
                        .addDrv(DrvType.Mars) // 风扇板默认驱动，适应老版风扇板的情况
                        .addSrvBrd(20001)
                        .addSrvBrd(20002) // 配置了两个业务板，槽位号分别为20001, 20002
                        .initFanBoxesSetting(fanBoxNode1)
                        .build()
        ).showStatus();

        // 默认为自动挡的情况下，此时进行的调速应该失败
        fanCtrlAction.manualAdjust(10000, FanSpeed.FAN_SPEED_HIGH).showStatus();
        // 查询一块不存在的风扇板，失败
        fanCtrlAction.manualAdjust(10004, FanSpeed.FAN_SPEED_MID).showStatus();

        // 将slot为10000的风扇板的模式改为手动挡
        fanCtrlAction.configFanMode(10000, FanBrdModeType.Manual).showStatus();
        // 设置为手动挡之后调速
        fanCtrlAction.manualAdjust(10000, FanSpeed.FAN_SPEED_HIGH).showStatus();

        // 根据槽位号查找业务板，模拟采集到的温度先后为100和40
        fanCtrlAction.configFanMode(10000, FanBrdModeType.Automatic).showStatus();
        fanCtrlAction.onSrvTempChanged(20001, 100);
        fanCtrlAction.onSrvTempChanged(20001, 40);
    }
}
