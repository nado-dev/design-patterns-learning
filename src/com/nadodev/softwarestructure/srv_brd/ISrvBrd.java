package com.nadodev.softwarestructure.srv_brd;

import com.nadodev.softwarestructure.Status;

public interface ISrvBrd {
    Status onTempChange(int temp);
    Status addListener(SrvBrdListener listener);
    int getBrdId();
}
