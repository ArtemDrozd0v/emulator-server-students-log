package org.example.converter.attendance_converter;

import org.example.entity.AttendanceEntity;
import org.example.model.AttendanceStatus;
import org.example.request.attendance_request.AddAttendanceRequest;
import org.example.request.attendance_request.EditAttendanceRequest;

import java.time.Instant;

public class AttendanceRequestConverter {
    public AttendanceEntity toEntity(AddAttendanceRequest request) {
        Instant now = Instant.now();
        long time = now.toEpochMilli();

        return new AttendanceEntity(
                null,
                request.getLessonId(),
                request.getStudentId(),
                AttendanceStatus.valueOf(request.getStatus().trim().toUpperCase()),
                time,
                time
        );
    }

    public AttendanceEntity toEntity(EditAttendanceRequest request, long createdAt) {
        Instant now = Instant.now();
        long time = now.toEpochMilli();

        return new AttendanceEntity(
                request.getId(),
                request.getLessonId(),
                request.getStudentId(),
                AttendanceStatus.valueOf(request.getStatus().trim().toUpperCase()),
                createdAt,
                time
        );
    }
}
