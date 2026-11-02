package org.example.exception;

import java.util.List;

public class ValidationsException extends ServerException {
    public ValidationsException(String message, int code, List<String> errors) {
        super(message, code, errors);
    }

    public ValidationsException(String message, Throwable cause, int code, List<String> errors) {
        super(message, cause, code, errors);
    }
}
