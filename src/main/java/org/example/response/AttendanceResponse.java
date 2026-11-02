package org.example.response;

import org.example.model.AttendanceStatus;

public class AttendanceResponse {
    private final long id;
    private final LessonResponse lesson;
    private final StudentResponse student;
    private final AttendanceStatus status;

    public AttendanceResponse(long id, LessonResponse lesson, StudentResponse student, AttendanceStatus status) {
        this.id = id;
        this.lesson = lesson;
        this.student = student;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public LessonResponse getLesson() {
        return lesson;
    }

    public StudentResponse getStudent() {
        return student;
    }

    public AttendanceStatus getStatus() {
        return status;
    }
}
