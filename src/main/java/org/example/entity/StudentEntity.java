package org.example.entity;

import org.example.model.StudentStatus;

import java.util.Objects;

public class StudentEntity {
    private final Long id;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final long groupId;
    private final StudentStatus status;
    private final long createdAt;
    private final long updatedAt;

    public StudentEntity(Long id, String lastName, String firstName, String middleName, long groupId, StudentStatus status, long createdAt, long updatedAt) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.groupId = groupId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public long getGroupId() {
        return groupId;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return id == that.id && groupId == that.groupId && createdAt == that.createdAt && updatedAt == that.updatedAt && Objects.equals(lastName, that.lastName) && Objects.equals(firstName, that.firstName) && Objects.equals(middleName, that.middleName) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, middleName, groupId, status, createdAt, updatedAt);
    }
}
