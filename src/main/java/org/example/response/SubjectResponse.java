package org.example.response;

public class SubjectResponse {
    private final long id;
    private final String name;

    public SubjectResponse(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
