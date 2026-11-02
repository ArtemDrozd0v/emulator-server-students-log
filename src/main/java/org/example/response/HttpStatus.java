package org.example.response;

public enum HttpStatus {
    OK(200),
    CREATED(201),
    BAD_REQUEST(400),
    NOT_FOUND(404),
    UNPROCESSABLE_ENTITY(422),
    SERVER_ERROR(500);

    final int code;

    HttpStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
