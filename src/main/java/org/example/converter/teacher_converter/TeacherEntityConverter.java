package org.example.converter.teacher_converter;

import org.example.entity.TeacherEntity;
import org.example.response.TeacherResponse;

public class TeacherEntityConverter {
    public TeacherResponse toResponse(TeacherEntity teacher) {
        return new TeacherResponse(
                teacher.getId(),
                teacher.getLastName(),
                teacher.getFirstName(),
                teacher.getMiddleName()
        );
    }
}
