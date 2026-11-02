package org.example.response;

public class GroupResponse {
    private final long id;
    private final String name;

    public GroupResponse(long id, String name) {
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
