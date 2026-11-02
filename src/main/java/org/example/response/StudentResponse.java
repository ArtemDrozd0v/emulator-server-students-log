package org.example.response;

import org.example.model.StudentStatus;

public class StudentResponse {
    private final long id;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final GroupResponse group;
    private final StudentStatus status;

    public StudentResponse(long id, String lastName, String firstName, String middleName, GroupResponse group, StudentStatus status) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.group = group;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public GroupResponse getGroup() {
        return group;
    }

    public StudentStatus getStatus() {
        return status;
    }
}
