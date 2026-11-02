package org.example.request.lesson_request;

import java.util.List;

public class AddLessonRequest {
    private final long subjectId;
    private final long teacherId;
    private final List<Long> groupIds;
    private final String date;
    private final int lessonNumber;

    public AddLessonRequest(long subjectId, long teacherId, List<Long> groupIds, String date, int lessonNumber) {
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.groupIds = groupIds;
        this.date = date;
        this.lessonNumber = lessonNumber;
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
