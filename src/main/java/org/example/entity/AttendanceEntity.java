package org.example.entity;

import org.example.model.AttendanceStatus;

import java.util.Objects;

public class AttendanceEntity {
    private final Long id;
    private final long lessonId;
    private final long studentId;
    private final AttendanceStatus status;
    private final long createdAt;
    private final long updatedAt;

    public AttendanceEntity(Long id, long lessonId, long studentId, AttendanceStatus status, long createdAt, long updatedAt) {
        this.id = id;
        this.lessonId = lessonId;
        this.studentId = studentId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public long getLessonId() {
        return lessonId;
    }

    public long getStudentId() {
        return studentId;
    }

    public AttendanceStatus getStatus() {
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
        AttendanceEntity that = (AttendanceEntity) o;
        return id == that.id && lessonId == that.lessonId && studentId == that.studentId && createdAt == that.createdAt && updatedAt == that.updatedAt && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lessonId, studentId, status, createdAt, updatedAt);
    }
}
