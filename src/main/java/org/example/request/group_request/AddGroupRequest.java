package org.example.request.group_request;

public class AddGroupRequest {
    private final String name;

    public AddGroupRequest(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }
}
