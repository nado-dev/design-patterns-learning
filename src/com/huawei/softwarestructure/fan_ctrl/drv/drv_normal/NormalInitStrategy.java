package com.huawei.softwarestructure.fan_ctrl.drv.drv_normal;

import com.huawei.softwarestructure.fan_ctrl.drv.IDrvInitStrategy;

public class NormalInitStrategy implements IDrvInitStrategy {
    @Override
    public void execute() {
        System.out.println("[NormalInitStrategy] execute NormalInitStrategy");
    }
}
