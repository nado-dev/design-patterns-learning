package com.huawei.softwarestructure.fan_ctrl.fan_box;

import com.huawei.softwarestructure.Status;
import com.huawei.softwarestructure.fan_ctrl.FanSpeed;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式：表示嵌套的风扇盒，将操作给其子风扇盒
 */
public class CompositeFanBox implements IFanBox{

    private final List<IFanBox> children = new ArrayList<>();

    public void add(IFanBox component) {
        children.add(component);
    }

    public void remove(IFanBox component) {
        children.remove(component);
    }

    @Override
    public Status adjustFanSpeed(FanSpeed fanSpeed) {
        System.out.println("Composite is being operated.");
        for (IFanBox component : children) {
            Status ret = component.adjustFanSpeed(fanSpeed);
            if (!ret.isSuccess()) return ret;
        }
        return Status.getSuccessStatus("[CompositeFanBox] operation done for children");
    }
}
