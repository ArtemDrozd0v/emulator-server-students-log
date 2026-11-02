package org.example.data_base;

import org.example.entity.*;

import java.util.Map;

public class DataBase {
    private final Map<Long, StudentEntity> students;
    private final Map<Long, GroupEntity> groups;
    private final Map<Long, TeacherEntity> teachers;
    private final Map<Long, SubjectEntity> subjects;
    private final Map<Long, LessonEntity> lessons;
    private final Map<Long, AttendanceEntity> attendances;

    private long finalStudentId;
    private long finalGroupId;
    private long finalTeacherId;
    private long finalSubjectId;
    private long finalLessonId;
    private long finalAttendanceId;

    public DataBase(Map<Long, StudentEntity> students, Map<Long, GroupEntity> groups, Map<Long, TeacherEntity> teachers, Map<Long, SubjectEntity> subjects, Map<Long, LessonEntity> lessons, Map<Long, AttendanceEntity> attendances) {
        this.students = students;
        this.groups = groups;
        this.teachers = teachers;
        this.subjects = subjects;
        this.lessons = lessons;
        this.attendances = attendances;
    }

    public Map<Long, StudentEntity> getStudents() {
        return students;
    }

    public Map<Long, GroupEntity> getGroups() {
        return groups;
    }

    public Map<Long, TeacherEntity> getTeachers() {
        return teachers;
    }

    public Map<Long, SubjectEntity> getSubjects() {
        return subjects;
    }

    public Map<Long, LessonEntity> getLessons() {
        return lessons;
    }

    public Map<Long, AttendanceEntity> getAttendances() {
        return attendances;
    }

    public long getFinalStudentId() {
        return finalStudentId;
    }

    public long getFinalGroupId() {
        return finalGroupId;
    }

    public long getFinalTeacherId() {
        return finalTeacherId;
    }

    public long getFinalSubjectId() {
        return finalSubjectId;
    }

    public long getFinalLessonId() {
        return finalLessonId;
    }

    public long getFinalAttendanceId() {
        return finalAttendanceId;
    }

    public long nextStudentId() {
        return ++finalStudentId;
    }

    public long nextGroupId() {
        return ++finalGroupId;
    }

    public long nextTeacherId() {
        return ++finalTeacherId;
    }

    public long nextSubjectId() {
        return ++finalSubjectId;
    }

    public long nextLessonId() {
        return ++finalLessonId;
    }

    public long nextAttendanceId() {
        return ++finalAttendanceId;
    }

}
