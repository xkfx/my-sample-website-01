package org.sample.webapp.dto;

public class ServiceResult<T> {

    private boolean success;

    private T data;

    private String error;

    public ServiceResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public ServiceResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public String getError() {
        return error;
    }

    @Override
    public java.lang.String toString() {
        return "ServiceResult{" +
                "success=" + success +
                ", data=" + data +
                ", error=" + error +
                '}';
    }
}