package com.huawei.softwarestructure.fan_ctrl;
/***********************************************************************
 * Module:  FanMgr.java
 * Author:  AARONFANG
 * Purpose: Defines the Class FanMgr
 ***********************************************************************/

import com.huawei.softwarestructure.Status;

import java.util.*;


public class FanMgrImpl implements IFanMgr {

    private static FanMgrImpl instance;

    private final List<IFanBrd> IFanBrdList;


    private FanMgrImpl() {
        IFanBrdList = new ArrayList<>();
    }


    public Status initConfig(FanBrdConfig cfg) {
        // 创建风扇板
        IFanBrd brd = FanBrdFactory.makeFanBrd(cfg);
        if (brd == null) {
            return Status.getFailStatus("Fan Board Create failed");
        }
        IFanBrdList.add(brd);
        // 配置业务板
        brd.configSrvBrds(cfg).showStatus();
        return Status.getSuccessStatus("创建风扇板成功");
    }


    public IFanBrd getFanBrdBySlotId(int sid) {
        for (IFanBrd brd : IFanBrdList) {
            if (brd.isMatch(sid)) return brd;
        }
        return null;
    }


    public static FanMgrImpl getInstance() {
        if (instance == null) {
            synchronized (FanMgrImpl.class) {
                if (instance == null) {
                    instance = new FanMgrImpl();
                }
            }
        }
        return instance;
    }


    public List<IFanBrd> getFanBrdList() {
        return IFanBrdList;
    }
}
