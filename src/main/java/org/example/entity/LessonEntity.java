package org.example.entity;

import java.util.List;

public class LessonEntity {
    private final Long id;
    private final long subjectId;
    private final long teacherId;
    private final List<Long> groupsId;
    private final String date;
    private final int lessonNumber;
    private final long createdAt;
    private final long updatedAt;

    public LessonEntity(Long id, long subjectId, long teacherId, List<Long> groupsId, String date, int lessonNumber, long createdAt, long updatedAt) {
        this.id = id;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.groupsId = groupsId;
        this.date = date;
        this.lessonNumber = lessonNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public List<Long> getGroupsId() {
        return groupsId;
    }

    public String getDate() {
        return date;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }
}
