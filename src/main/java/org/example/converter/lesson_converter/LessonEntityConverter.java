package org.example.converter.lesson_converter;

import org.example.entity.GroupEntity;
import org.example.entity.LessonEntity;
import org.example.entity.SubjectEntity;
import org.example.entity.TeacherEntity;
import org.example.response.*;

import java.util.List;

public class LessonEntityConverter {
    public LessonResponse toResponse(LessonEntity lesson, SubjectEntity subject, TeacherEntity teacher, List<GroupEntity> groups) {
        return new LessonResponse(
                lesson.getId(),
                new SubjectResponse(
                        subject.getId(),
                        subject.getName()
                ),
                new TeacherResponse(
                        teacher.getId(),
                        teacher.getLastName(),
                        teacher.getFirstName(),
                        teacher.getMiddleName()
                ),
                groups.stream()
                        .map(group -> new GroupResponse(group.getId(), group.getName()))
                        .toList(),
                lesson.getDate(),
                lesson.getLessonNumber()
        );
    }

    public LessonWithAttendanceResponse toLessonWithAttendanceResponse(LessonEntity lesson, SubjectEntity subject, TeacherEntity teacher, List<GroupEntity> groups, List<AttendanceResponse> attendance) {
        return new LessonWithAttendanceResponse(
                lesson.getId(),
                new SubjectResponse(
                        subject.getId(),
                        subject.getName()
                ),
                new TeacherResponse(
                        teacher.getId(),
                        teacher.getLastName(),
                        teacher.getFirstName(),
                        teacher.getMiddleName()
                ),
                groups.stream()
                        .map(group -> new GroupResponse(group.getId(), group.getName()))
                        .toList(),
                lesson.getDate(),
                lesson.getLessonNumber(),
                attendance
        );
    }
}
