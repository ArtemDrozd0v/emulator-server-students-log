package org.example.entity;

import java.util.Objects;

public class GroupEntity {
    private final Long id;
    private final String name;
    private final long createdAt;
    private final long updatedAt;

    public GroupEntity(Long id, String name, long createdAt, long updatedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
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
        GroupEntity that = (GroupEntity) o;
        return id == that.id && createdAt == that.createdAt && updatedAt == that.updatedAt && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdAt, updatedAt);
    }
}
