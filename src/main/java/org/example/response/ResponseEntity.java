package org.example.response;

public class ResponseEntity<T> {
    private HttpStatus status;
    private T body;

    public ResponseEntity(HttpStatus status, T body) {
        this.status = status;
        this.body = body;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public T getBody() {
        return body;
    }
}
