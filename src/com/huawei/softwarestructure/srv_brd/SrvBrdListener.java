package com.huawei.softwarestructure.srv_brd;

import com.huawei.softwarestructure.Status;

public interface SrvBrdListener {
    Status onSrvHot(int slot, int temp);
}
