package com.huawei.softwarestructure.fan_ctrl;

import com.huawei.softwarestructure.fan_ctrl.drv.DrvType;

import java.util.ArrayList;
import java.util.List;

/***********************************************************************
 * Module:  FanBrdConfig.java
 * Author:  AARONFANG
 * Purpose: Defines the Class FanBrdConfig
 ***********************************************************************/

public class FanBrdConfig {
    /**
     * 风扇板的槽位号
     */
    private int slot;

    /**
     * 风扇盒的层级结构
     */
    private FanBoxNode fanBoxNode;

    private DrvType commType;

    /**
     * 风扇板管理的业务板逻辑对象列表
     */
    private List<Integer> srvBrdSlotList;

    public FanBrdConfig(int slotNum, DrvType commType, List<Integer> srvBrdSlotList){
        this.slot = slotNum;
        this.commType = commType;
        this.srvBrdSlotList = srvBrdSlotList;
    }

    public FanBrdConfig(int slotNum, DrvType commType, List<Integer> srvBrdSlotList, FanBoxNode fanBoxNode){
        this.slot = slotNum;
        this.commType = commType;
        this.srvBrdSlotList = srvBrdSlotList;
        this.fanBoxNode = fanBoxNode;
    }


    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }

    public DrvType getCommType() {
        return commType;
    }

    public void setCommType(DrvType commType) {
        this.commType = commType;
    }

    public List<Integer> getSrvBrdSlotList() {
        return srvBrdSlotList;
    }

    public void setSrvBrdSlotList(List<Integer> srvBrdSlotList) {
        this.srvBrdSlotList = srvBrdSlotList;
    }

    /**
     * Builder模式，用于优雅地构建复杂的的FanBrdConfig对象
     */
    public static class FanBrdConfigBuilder {
        private int slot;
        private final List<Integer> srvBrdSlotList = new ArrayList<>();
        private DrvType commType;
        private FanBoxNode fanBoxNode;

        public FanBrdConfigBuilder addFanBrdSlot(int slot) {
            this.slot = slot;
            return this;
        }

        public FanBrdConfigBuilder initFanBoxesSetting(FanBoxNode fanBoxNode) {
            this.fanBoxNode = fanBoxNode;
            return this;
        }

        public FanBrdConfigBuilder addDrv(DrvType commType) {
            this.commType = commType;
            return this;
        }

        public FanBrdConfigBuilder addSrvBrd(int slot) {
            srvBrdSlotList.add(slot);
            return this;
        }

        public FanBrdConfig build() {
            return new FanBrdConfig(slot, commType, srvBrdSlotList, fanBoxNode);
        }
    }


    public static class FanBoxNode {
        private int slot;
        private DrvType drvType = DrvType.UNDEFINED;
        private final List<FanBoxNode> children = new ArrayList<>();

        public FanBoxNode(int slot) {
            this.slot = slot;
        }

        public FanBoxNode(int slot, DrvType drvType) {
            this.drvType = drvType;
            this.slot = slot;
        }

        public boolean isLeaveNode() {
            return children.size() == 0;
        }

        public void addChild(FanBoxNode fanBoxNode) {
            children.add(fanBoxNode);
        }

    }

    public static class NodeBuilder {
        private int slot;
        private DrvType drvType = DrvType.UNDEFINED;
        private final List<NodeBuilder> children = new ArrayList<>();

        public NodeBuilder(int slot) {
            this.slot = slot;
        }

        public NodeBuilder(int slot, DrvType drvType) {
            this.drvType = drvType;
            this.slot = slot;
        }

        public NodeBuilder addChild(NodeBuilder nodeBuilder) {
            children.add(nodeBuilder);
            return this;
        }

        public FanBoxNode build() {
            FanBoxNode node;
            if (drvType == DrvType.UNDEFINED)
                node = new FanBoxNode(slot);
            else
                node = new FanBoxNode(slot, drvType);
            for (NodeBuilder child : children) {
                node.addChild(child.build());
            }
            return node;
        }
    }
}