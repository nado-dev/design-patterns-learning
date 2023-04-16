package com.nadodev.softwarestructure.fan_ctrl;

import com.nadodev.softwarestructure.Status;

import java.util.List;

public interface IFanMgr {

    Status initConfig(FanBrdConfig cfg);
    List<IFanBrd> getFanBrdList();
    IFanBrd getFanBrdBySlotId(int sid);


}
