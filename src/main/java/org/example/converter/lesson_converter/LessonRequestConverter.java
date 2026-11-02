package org.example.converter.lesson_converter;

import org.example.entity.LessonEntity;
import org.example.request.lesson_request.AddLessonRequest;
import org.example.request.lesson_request.EditLessonRequest;

import java.time.Instant;

public class LessonRequestConverter {
    public LessonEntity toEntity(AddLessonRequest request) {
        Instant now = Instant.now();
        long time = now.toEpochMilli();

        return new LessonEntity(
                null,
                request.getSubjectId(),
                request.getTeacherId(),
                request.getGroupIds(),
                request.getDate(),
                request.getLessonNumber(),
                time,
                time
        );
    }

    public LessonEntity toEntity(EditLessonRequest request, long createdAt) {
        Instant now = Instant.now();
        long time = now.toEpochMilli();

        return new LessonEntity(
                request.getId(),
                request.getSubjectId(),
                request.getTeacherId(),
                request.getGroupIds(),
                request.getDate(),
                request.getLessonNumber(),
                createdAt,
                time
        );
    }
}
