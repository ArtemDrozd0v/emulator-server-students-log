package org.example.exception;

import java.util.List;

public class ServerException extends Exception {
    private int code;
    private List<String> errors;

    public ServerException(String message, int code, List<String> errors) {
        super(message);
        this.code = code;
        this.errors = errors;
    }

    public ServerException(String message, Throwable cause, int code, List<String> errors) {
        super(message, cause);
        this.code = code;
        this.errors = errors;
    }

    public int getCode() {
        return code;
    }

    public List<String> getErrors() {
        return errors;
    }
}
