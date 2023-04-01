package com.huawei.softwarestructure;
/***********************************************************************
 * Module:  FanMgr.java
 * Author:  AARONFANG
 * Purpose: Defines the Class FanMgr
 ***********************************************************************/

import java.util.*;


public class FanMgr {

    private static FanMgr instance;

    private final List<FanBrd> fanBrdList;


    private FanMgr() {
        fanBrdList = new ArrayList<>();
    }


    public Status initConfig(FanBrdConfig cfg) {
        FanBrd brd = FanBrdFactory.makeFanBrd(cfg);
        fanBrdList.add(brd);
        return null;
    }


    public FanBrd getFanBrdBySlotId(int sid) {
        for (FanBrd brd : fanBrdList) {
            if (brd.isMatch(sid)) return brd;
        }
        return null;
    }


    public static FanMgr getInstance() {
        if (instance == null) {
            synchronized (FanMgr.class) {
                if (instance == null) {
                    instance = new FanMgr();
                }
            }
        }
        return instance;
    }


    public List<FanBrd> getFanBrdList() {
        return fanBrdList;
    }
}
