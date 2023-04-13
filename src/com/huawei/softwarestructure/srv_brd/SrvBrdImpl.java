package com.huawei.softwarestructure.srv_brd;

import com.huawei.softwarestructure.Alarm;
import com.huawei.softwarestructure.Status;

import java.util.ArrayList;
import java.util.List;

public class SrvBrdImpl implements ISrvBrd{

    private int slot;
    private int temp;
    List<SrvBrdListener> listeners = new ArrayList<>();

    SrvBrdImpl(int slot) {
        this.slot = slot;
        // 默认添加报警器作为Listener
        addListener(Alarm.getInstance());
    }

    public Status notifyTempChanged() {
        for (SrvBrdListener listener : listeners) {
            Status status = listener.onSrvHot(slot, temp);
            if (!status.isSuccess()) {
                return status;
            }
        }
        return Status.getSuccessStatus("state hot");
    }

    @Override
    public Status onTempChange(int temp) {
        if (temp == this.temp) {
            return Status.getSuccessStatus("same temp, failed to change");
        }
        this.temp = temp;
        return notifyTempChanged();
    }

    @Override
    public Status addListener(SrvBrdListener listener) {
        listeners.add(listener);
        return Status.getSuccessStatus("listener added");
    }

    @Override
    public int getBrdId() {
        return slot;
    }

    public void setId(int slot) {
        this.slot = slot;
    }

    @Override
    public String toString() {
        return "SrvBrdImpl{" +
                "slot=" + slot +
                ", temp=" + temp +
                '}';
    }
}
