package org.example.converter.subject_converter;

import org.example.entity.SubjectEntity;
import org.example.response.SubjectResponse;

public class SubjectEntityConverter {
    public SubjectResponse toResponse(SubjectEntity subject) {
        return new SubjectResponse(
                subject.getId(),
                subject.getName()
        );
    }
}
