package com.huawei.softwarestructure.fan_ctrl;

import com.huawei.softwarestructure.Status;

import java.util.List;

public interface IFanMgr {

    Status initConfig(FanBrdConfig cfg);
    List<IFanBrd> getFanBrdList();
    IFanBrd getFanBrdBySlotId(int sid);


}
