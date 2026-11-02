package org.example.response;

import java.util.List;

public class LessonResponse {
    private final long id;
    private final SubjectResponse subject;
    private final TeacherResponse teacher;
    private final List<GroupResponse> groups;
    private final String date;
    private final int lessonNumber;

    public LessonResponse(long id, SubjectResponse subject, TeacherResponse teacher, List<GroupResponse> groups, String date, int lessonNumber) {
        this.id = id;
        this.subject = subject;
        this.teacher = teacher;
        this.groups = groups;
        this.date = date;
        this.lessonNumber = lessonNumber;
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
}
