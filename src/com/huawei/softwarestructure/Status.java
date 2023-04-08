package com.huawei.softwarestructure;

public class Status {
    private String desc;
    private boolean result;

    public Status(boolean result, String desc) {
        this.desc = desc;
        this.result = result;
    }

    static Status getSuccessStatus() {
        return new Status(true, "");
    }

    static Status getFailStatus() {
        return new Status(false, "");
    }
}
