package org.example.converter.student_converter;

import org.example.entity.StudentEntity;
import org.example.model.StudentStatus;
import org.example.request.student_request.AddStudentRequest;
import org.example.request.student_request.EditStudentRequest;

import java.time.Instant;

public class StudentRequestConverter {
    public StudentEntity toEntity(AddStudentRequest request) {
        Instant now = Instant.now();
        long time = now.toEpochMilli();
    
        return new StudentEntity(
                null,
                request.getLastName(),
                request.getFirstName(),
                request.getMiddleName(),
                request.getGroupId(),
                StudentStatus.valueOf(request.getStatus().trim().toUpperCase()),
                time,
                time
        );
    }

    public StudentEntity toEntity(EditStudentRequest request, long createdAt) {
        Instant now = Instant.now();
        long time = now.toEpochMilli();

        return new StudentEntity(
                request.getId(),
                request.getLastName(),
                request.getFirstName(),
                request.getMiddleName(),
                request.getGroupId(),
                StudentStatus.valueOf(request.getStatus().trim().toUpperCase()),
                createdAt,
                time
        );
    }

}
