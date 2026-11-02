package org.example.converter.attendance_converter;

import org.example.entity.*;
import org.example.response.*;

import java.util.List;

public class AttendanceEntityConverter {
    public AttendanceResponse toResponse(AttendanceEntity attendance, LessonEntity lesson, StudentEntity student,
                                         GroupEntity studentGroup, List<GroupEntity> lessonGroups,
                                         SubjectEntity subject, TeacherEntity teacher) {

        return new AttendanceResponse(
                attendance.getId(),
                new LessonResponse(
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
                        lessonGroups.stream()
                                .map(group -> new GroupResponse(group.getId(), group.getName()))
                                .toList(),
                        lesson.getDate(),
                        lesson.getLessonNumber()
                ),
                new StudentResponse(
                        student.getId(),
                        student.getLastName(),
                        student.getFirstName(),
                        student.getMiddleName(),
                        new GroupResponse(
                                studentGroup.getId(),
                                studentGroup.getName()
                        ),
                        student.getStatus()
                ),
                attendance.getStatus()
        );
    }
}
