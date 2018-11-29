package org.sample.webapp.dto;

import org.sample.webapp.enums.OPEnum;

public class OPResult<T> {

    private boolean success;

    private T data;

    private OPEnum extraInfo;

    public OPResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public OPResult(boolean success, OPEnum extraInfo) {
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

    public OPEnum getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(OPEnum extraInfo) {
        this.extraInfo = extraInfo;
    }

    @Override
    public String toString() {
        return "OPResult{" +
                "success=" + success +
                ", data=" + data +
                ", extraInfo=" + extraInfo +
                '}';
    }
}