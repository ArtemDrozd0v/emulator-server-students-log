package org.example.repository.attendace_repository;

import org.example.entity.AttendanceEntity;

import java.util.List;

public interface IAttendanceRepository {
    long create(AttendanceEntity attendance);
    AttendanceEntity getById(long id);

    List<AttendanceEntity> getAll();
    List<AttendanceEntity> getByLessonId(long lessonId);
    List<AttendanceEntity> getByStudentId(long studentId);

    void update(AttendanceEntity attendance);
    void deleteById(long id);
}


