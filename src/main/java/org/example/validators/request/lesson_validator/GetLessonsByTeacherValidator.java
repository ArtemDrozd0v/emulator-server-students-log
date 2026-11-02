package org.example.validators.request.lesson_validator;

import org.example.request.lesson_request.GetLessonsByTeacherRequest;
import org.example.validators.primitive.DateValidator;
import org.example.validators.primitive.IdValidator;
import org.example.validators.request.RequestValidator;

import java.util.ArrayList;
import java.util.List;

public class GetLessonsByTeacherValidator implements RequestValidator<GetLessonsByTeacherRequest> {
    private final IdValidator idValidator;
    private final DateValidator dateValidator;

    public GetLessonsByTeacherValidator(IdValidator idValidator, DateValidator dateValidator) {
        this.idValidator = idValidator;
        this.dateValidator = dateValidator;
    }

    @Override
    public List<String> validate(GetLessonsByTeacherRequest request) {
        List<String> errors = new ArrayList<>();

        dateValidator.isDate(request.getStartDate(), errors, "startDate");
        dateValidator.isDate(request.getEndDate(), errors, "endDate");
        idValidator.isId(request.getTeacherId(), errors, "teacherId");

        return errors;
    }
}
