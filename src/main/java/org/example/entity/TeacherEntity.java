package org.example.entity;

import java.util.Objects;

public class TeacherEntity {
    private final Long id;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final long createdAt;
    private final long updatedAt;

    public TeacherEntity(Long id, String lastName, String firstName, String middleName, long createdAt, long updatedAt) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
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

    public long getCreatedAt() {
        return createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TeacherEntity that = (TeacherEntity) o;
        return id == that.id && createdAt == that.createdAt && updatedAt == that.updatedAt && Objects.equals(lastName, that.lastName) && Objects.equals(firstName, that.firstName) && Objects.equals(middleName, that.middleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, middleName, createdAt, updatedAt);
    }
}
