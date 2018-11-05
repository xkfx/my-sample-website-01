package org.sample.dto;

import org.sample.enums.OnesProfileEnum;

public class OnesProfileResult<T> {

    private boolean success;

    private T data;

    private OnesProfileEnum extraInfo;

    public OnesProfileResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public OnesProfileResult(boolean success, OnesProfileEnum extraInfo) {
        this.success = success;
        this.extraInfo = extraInfo;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public OnesProfileEnum getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(OnesProfileEnum extraInfo) {
        this.extraInfo = extraInfo;
    }
}