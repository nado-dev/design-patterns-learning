package com.nadodev.softwarestructure.fan_ctrl;

import com.nadodev.softwarestructure.fan_ctrl.drv.DrvType;

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

    /**
     * 风扇板的总驱动配置
     */
    private DrvType commType;

    /**
     * 风扇板管理的业务板逻辑对象列表
     */
    private final List<Integer> srvBrdSlotList;

    public FanBrdConfig(int slotNum, DrvType commType, List<Integer> srvBrdSlotList, FanBoxNode fanBoxNode){
        this.slot = slotNum;
        this.commType = commType;
        this.srvBrdSlotList = srvBrdSlotList;
        this.fanBoxNode = fanBoxNode;
    }

    public FanBoxNode getFanBoxNode() {
        return fanBoxNode;
    }

    public int getSlot() {
        return slot;
    }

    public DrvType getCommType() {
        return commType;
    }

    public List<Integer> getSrvBrdSlotList() {
        return srvBrdSlotList;
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


    /**
     * 表示风扇盒配置层级信息
     */
    public static class FanBoxNode {
        public int slot;
        public DrvType drvType = DrvType.UNDEFINED;
        public final List<FanBoxNode> children = new ArrayList<>();

        public FanBoxNode(int slot) {
            this.slot = slot;
        }

        public FanBoxNode(int slot, DrvType drvType) {
            this.drvType = drvType;
            this.slot = slot;
        }

        public boolean isLeafNode() {
            return children.size() == 0;
        }

        public void addChild(FanBoxNode fanBoxNode) {
            children.add(fanBoxNode);
        }

    }

    /**
     * FanBoxNode的构建器
     */
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