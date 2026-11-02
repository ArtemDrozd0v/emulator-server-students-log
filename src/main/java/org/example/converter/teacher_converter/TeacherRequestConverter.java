package org.example.converter.teacher_converter;

import org.example.entity.TeacherEntity;
import org.example.request.teacher_request.AddTeacherRequest;
import org.example.request.teacher_request.EditTeacherRequest;

import java.time.Instant;

public class TeacherRequestConverter {
    public TeacherEntity toEntity(AddTeacherRequest request) {
        Instant now = Instant.now();
        long time = now.toEpochMilli();

        return new TeacherEntity(
                null,
                request.getLastName(),
                request.getFirstName(),
                request.getMiddleName(),
                time,
                time
        );
    }

    public TeacherEntity toEntity(EditTeacherRequest request, long createdAt) {
        Instant now = Instant.now();
        long time = now.toEpochMilli();

        return new TeacherEntity(
                request.getId(),
                request.getLastName(),
                request.getFirstName(),
                request.getMiddleName(),
                createdAt,
                time
        );
    }

}
