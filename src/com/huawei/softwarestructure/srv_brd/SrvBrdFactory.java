package com.huawei.softwarestructure.srv_brd;

public class SrvBrdFactory {

    private SrvBrdFactory() {

    }
    public static ISrvBrd makeSrvBrd(int slot) {
        return new SrvBrdImpl(slot);
    }
}
