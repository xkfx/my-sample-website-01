package org.sample.webapp.dto;

public class OPResult<T> {

    private boolean success;

    private T data;

    private String error;

    public OPResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public OPResult(boolean success, String error) {
        this.success = success;
        this.error = error;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public java.lang.String toString() {
        return "OPResult{" +
                "success=" + success +
                ", data=" + data +
                ", error=" + error +
                '}';
    }
}