package com.nadodev.softwarestructure.fan_ctrl.fan_box;

import com.nadodev.softwarestructure.fan_ctrl.FanBrdConfig;
import com.nadodev.softwarestructure.fan_ctrl.drv.DrvContext;
import com.nadodev.softwarestructure.fan_ctrl.drv.DrvType;
import com.nadodev.softwarestructure.fan_ctrl.drv.IFanDrv;
import com.nadodev.softwarestructure.fan_ctrl.drv.NilFanDrv;
import com.nadodev.softwarestructure.fan_ctrl.drv.drv_neptune.NeptuneFanDrv;
import com.nadodev.softwarestructure.fan_ctrl.drv.drv_neptune.NeptuneFanDrvAdapter;
import com.nadodev.softwarestructure.fan_ctrl.drv.drv_neptune.NeptuneInitStrategy;
import com.nadodev.softwarestructure.fan_ctrl.drv.drv_normal.MarsFanDrv;
import com.nadodev.softwarestructure.fan_ctrl.drv.drv_normal.NormalInitStrategy;
import com.nadodev.softwarestructure.fan_ctrl.drv.drv_normal.VenusFanDrv;

public class FanBoxUtils {

    /**
     * 将FanBrdConfig.FanBoxNode描述风扇信息转换为IFanBox
     * @param fanBoxNode 配置文件信息
     * @return FanBox抽象
     */
    public static IFanBox copyFrom(FanBrdConfig.FanBoxNode fanBoxNode) {
        if (fanBoxNode.isLeafNode())
            return new ConcreteFanBox(fanBoxNode.slot, fanBoxNode.drvType);
        // 复合层级
        CompositeFanBox compositeFanBox = new CompositeFanBox();
        for (FanBrdConfig.FanBoxNode child : fanBoxNode.children) {
            compositeFanBox.add(copyFrom(child));
        }
        return compositeFanBox;
    }

    /**
     * 根据策略模式执行初始化策略
     * @param drvType 模式
     */
    public static void executeStrategy(DrvType drvType) {
        DrvContext drvContext = DrvContext.getInstance();
        if (drvType == DrvType.Neptune) {
            drvContext.setStrategy(new NeptuneInitStrategy());
        }
        else if (drvType== DrvType.Mars){
            drvContext.setStrategy(new NormalInitStrategy());
        }
        else if (drvType== DrvType.Venus) {
            drvContext.setStrategy(new NormalInitStrategy());
        }
        drvContext.executeStrategy();
    }

    public static IFanDrv getFanDrvByType(DrvType drvType) {
        if (drvType == DrvType.Neptune) {
            return new NeptuneFanDrvAdapter(new NeptuneFanDrv());
        }
        else if (drvType== DrvType.Mars){
            return new MarsFanDrv();
        }
        else if (drvType == DrvType.Venus) {
            return new VenusFanDrv();
        }
        else
            return new NilFanDrv();
    }
}
