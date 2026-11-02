package org.example.repository.attendace_repository;

import org.example.data_base.DataBase;
import org.example.entity.AttendanceEntity;

import java.util.List;

public class AttendanceRepository implements IAttendanceRepository {
    private final DataBase dataBase;

    public AttendanceRepository(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public long create(AttendanceEntity attendance) {
        long id = dataBase.nextAttendanceId();

        AttendanceEntity savedAttendance = new AttendanceEntity(
                id,
                attendance.getLessonId(),
                attendance.getStudentId(),
                attendance.getStatus(),
                attendance.getCreatedAt(),
                attendance.getUpdatedAt()
        );

        dataBase.getAttendances().put(id, savedAttendance);
        return id;
    }

    @Override
    public AttendanceEntity getById(long id) {
        return dataBase.getAttendances().get(id);
    }

    @Override
    public List<AttendanceEntity> getAll() {
        return dataBase.getAttendances().values().stream().toList();
    }

    @Override
    public List<AttendanceEntity> getByLessonId(long lessonId) {
        return dataBase.getAttendances().values().stream()
                .filter(attendance -> attendance.getLessonId() == lessonId)
                .toList();
    }

    @Override
    public List<AttendanceEntity> getByStudentId(long studentId) {
        return dataBase.getAttendances().values().stream()
                .filter(attendance -> attendance.getStudentId() == studentId)
                .toList();
    }

    @Override
    public void update(AttendanceEntity attendance) {
        dataBase.getAttendances().put(attendance.getId(), attendance);
    }

    @Override
    public void deleteById(long id) {
        dataBase.getAttendances().remove(id);
    }
}
