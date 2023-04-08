package com.huawei.softwarestructure.srv_brd;

import com.huawei.softwarestructure.Status;

public interface ISrvBrd {
    Status onTempChange(int temp);
    Status addListener(SrvBrdListener listener);
    int getBrdId();
}
