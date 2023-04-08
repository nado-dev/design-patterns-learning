package com.huawei.softwarestructure;

import com.huawei.softwarestructure.ctrl.FanCtrlAction;
import com.huawei.softwarestructure.fan_ctrl.*;
import com.huawei.softwarestructure.srv_brd.ISrvBrd;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        // 第一次作业
	    FanCtrlAction fanCtrlAction = new FanCtrlAction(FanMgrImpl.getInstance());

        List<Integer> fanBrd1SrvBrd = new ArrayList<>(); // 第一块风扇板(slot:10000)管理的业务板(slot:20001,20002)
        fanBrd1SrvBrd.add(20001);
        fanBrd1SrvBrd.add(20002);
	    fanCtrlAction.initFanBrdConfig(new FanBrdConfig(10000, "type1", fanBrd1SrvBrd)).showStatus();

        List<Integer> fanBrd2SrvBrd = new ArrayList<>();// 第二块风扇板(slot:10001)管理的业务板(slot:20003,20004)
        fanBrd2SrvBrd.add(20003);
        fanBrd2SrvBrd.add(20004);
        fanCtrlAction.initFanBrdConfig(new FanBrdConfig(10001, "type1", fanBrd2SrvBrd)).showStatus();

        List<Integer> fanBrd3SrvBrd = new ArrayList<>();// 第三块风扇板(slot:10003)管理的业务板(slot:20005,20006)
        fanBrd3SrvBrd.add(20005);
        fanBrd3SrvBrd.add(20006);
        fanCtrlAction.initFanBrdConfig(new FanBrdConfig(10002, "type1", fanBrd3SrvBrd)).showStatus();

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
