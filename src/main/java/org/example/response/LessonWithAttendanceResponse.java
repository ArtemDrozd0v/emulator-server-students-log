package org.example.response;

import java.util.List;

public class LessonWithAttendanceResponse {
    private final long id;
    private final SubjectResponse subject;
    private final TeacherResponse teacher;
    private final List<GroupResponse> groups;
    private final String date;
    private final int lessonNumber;
    private final List<AttendanceResponse> attendance;

    public LessonWithAttendanceResponse(long id, SubjectResponse subject, TeacherResponse teacher, List<GroupResponse> groups, String date, int lessonNumber, List<AttendanceResponse> attendance) {
        this.id = id;
        this.subject = subject;
        this.teacher = teacher;
        this.groups = groups;
        this.date = date;
        this.lessonNumber = lessonNumber;
        this.attendance = attendance;
    }

    public long getId() {
        return id;
    }

    public SubjectResponse getSubject() {
        return subject;
    }

    public TeacherResponse getTeacher() {
        return teacher;
    }

    public List<GroupResponse> getGroups() {
        return groups;
    }

    public String getDate() {
        return date;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public List<AttendanceResponse> getAttendance() {
        return attendance;
    }
}
