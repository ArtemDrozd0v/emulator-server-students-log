package org.example.response;

import java.util.List;

public class CommonResponse<T> {
    private boolean success;
    private T data;
    private Integer errorCode;
    private List<String> errors;

    public CommonResponse(T data) {
        success = true;
        this.data = data;
    }

    public CommonResponse(Integer errorCode, List<String> errors) {
        success = false;
        this.errorCode = errorCode;
        this.errors = errors;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public List<String> getErrors() {
        return errors;
    }
}
