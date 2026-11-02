package org.example.request.lesson_request;

import java.util.List;

public class EditLessonRequest {
    private final long id;
    private final long subjectId;
    private final long teacherId;
    private final List<Long> groupIds;
    private final String date;
    private final int lessonNumber;

    public EditLessonRequest(long id, long subjectId, long teacherId, List<Long> groupIds, String date, int lessonNumber) {
        this.id = id;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.groupIds = groupIds;
        this.date = date;
        this.lessonNumber = lessonNumber;
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

    public List<Long> getGroupIds() {
        return groupIds;
    }

    public String getDate() {
        return date;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }
}
