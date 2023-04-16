package com.nadodev.softwarestructure.fan_ctrl.drv;

public class DrvContext {

    private static DrvContext instance;
    private IDrvInitStrategy strategy;

    private DrvContext() {}

    public static DrvContext getInstance() {
        if (instance == null) {
            synchronized (DrvContext.class) {
                if (instance == null) {
                    instance = new DrvContext();
                }
            }
        }
        return instance;
    }

    public void setStrategy(IDrvInitStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy() {
        if (strategy == null) return;
        strategy.execute();
    }
}
