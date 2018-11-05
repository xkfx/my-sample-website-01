package org.sample.dto;

import org.sample.enums.OnesProfileEnum;

public class Validation {

    private boolean success;

    private OnesProfileEnum error;

    public Validation(boolean success) {
        this.success = success;
    }

    public Validation(boolean success, OnesProfileEnum error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public OnesProfileEnum getError() {
        return error;
    }

    public void setError(OnesProfileEnum error) {
        this.error = error;
    }
}
