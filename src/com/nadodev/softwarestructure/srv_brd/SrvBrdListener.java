package com.nadodev.softwarestructure.srv_brd;

import com.nadodev.softwarestructure.Status;

public interface SrvBrdListener {
    Status onSrvHot(int slot, int temp);
}
