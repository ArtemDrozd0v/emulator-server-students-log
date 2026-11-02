package org.example.converter.subject_converter;

import org.example.entity.SubjectEntity;
import org.example.request.subject_request.AddSubjectRequest;
import org.example.request.subject_request.EditSubjectRequest;

import java.time.Instant;

public class SubjectRequestConverter {
    public SubjectEntity toEntity(AddSubjectRequest request) {
        Instant now = Instant.now();
        long time = now.toEpochMilli();

        return new SubjectEntity(
                null,
                request.getName(),
                time,
                time
        );
    }

    public SubjectEntity toEntity(EditSubjectRequest request, long createdAt) {
        Instant now = Instant.now();
        long time = now.toEpochMilli();

        return new SubjectEntity(
                request.getId(),
                request.getName(),
                createdAt,
                time
        );
    }
}
