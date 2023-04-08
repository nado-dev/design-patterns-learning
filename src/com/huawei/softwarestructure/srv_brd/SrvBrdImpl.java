package com.huawei.softwarestructure.srv_brd;

import com.huawei.softwarestructure.Status;

public class SrvBrdImpl implements ISrvBrd{

    private int id;

    @Override
    public Status onTempChange(int temp) {
        return null;
    }

    @Override
    public Status addListener(SrvBrdListener listener) {
        return null;
    }

    @Override
    public int getBrdId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
