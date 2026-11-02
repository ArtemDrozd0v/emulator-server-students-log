package org.example.request.attendance_request;

public class EditAttendanceRequest {
    private final long id;
    private final long lessonId;
    private final long studentId;
    private final String status;

    public EditAttendanceRequest(long id, long lessonId, long studentId, String status) {
        this.id = id;
        this.lessonId = lessonId;
        this.studentId = studentId;
        this.status = status;
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

    public String getStatus() {
        return status;
    }
}
