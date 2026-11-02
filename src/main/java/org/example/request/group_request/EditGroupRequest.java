package org.example.request.group_request;

public class EditGroupRequest {
    private final long id;
    private final String name;

    public EditGroupRequest(long id, String name) {
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
