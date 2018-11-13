package org.sample.webapp.dto;

import org.sample.webapp.enums.OPEnum;

public class Validation {

    private boolean success;

    private OPEnum error;

    public Validation(boolean success) {
        this.success = success;
    }

    public Validation(boolean success, OPEnum error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public OPEnum getError() {
        return error;
    }

    public void setError(OPEnum error) {
        this.error = error;
    }
}
