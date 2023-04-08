package com.huawei.softwarestructure;

public class Status {
    private String desc;
    private boolean result;

    public Status(boolean result, String desc) {
        this.desc = desc;
        this.result = result;
    }

    public static Status getSuccessStatus(String desc) {
        return new Status(true, desc);
    }

    public static Status getFailStatus(String desc) {
        return new Status(false, desc);
    }

    @Override
    public String toString() {
        return "Status{" +
                "desc='" + desc + '\'' +
                ", result=" + result +
                '}';
    }

    public void showStatus() {
        System.out.println(toString());
    }
}
