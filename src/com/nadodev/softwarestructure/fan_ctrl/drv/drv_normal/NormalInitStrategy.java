package com.nadodev.softwarestructure.fan_ctrl.drv.drv_normal;

import com.nadodev.softwarestructure.fan_ctrl.drv.IDrvInitStrategy;

public class NormalInitStrategy implements IDrvInitStrategy {
    @Override
    public void execute() {
        System.out.println("[NormalInitStrategy] execute NormalInitStrategy");
    }
}
