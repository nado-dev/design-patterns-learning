package com.huawei.softwarestructure.fan_ctrl.drv.drv_neptune;

import com.huawei.softwarestructure.fan_ctrl.drv.IDrvInitStrategy;

public class NeptuneInitStrategy implements IDrvInitStrategy {
    @Override
    public void execute() {
        System.out.println("[NeptuneInitStrategy] execute NeptuneInitStrategy");
    }
}
