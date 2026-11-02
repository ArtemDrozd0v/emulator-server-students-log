package org.example.validators.request.lesson_validator;

import org.example.request.lesson_request.AddLessonRequest;
import org.example.validators.primitive.IdValidator;
import org.example.validators.primitive.DateValidator;
import org.example.validators.request.RequestValidator;

import java.util.ArrayList;
import java.util.List;

public class AddLessonValidator implements RequestValidator<AddLessonRequest> {
    private final IdValidator idValidator;
    private final DateValidator dateValidator;

    public AddLessonValidator(IdValidator idValidator, DateValidator dateValidator) {
        this.idValidator = idValidator;
        this.dateValidator = dateValidator;
    }

    @Override
    public List<String> validate(AddLessonRequest request) {
        List<String> errors = new ArrayList<>();

        idValidator.isId(request.getSubjectId(), errors, "subject id");

        idValidator.isId(request.getTeacherId(), errors, "teacher id");

        idValidator.isId(request.getLessonNumber(), errors, "lesson number");

        for (Long groupId : request.getGroupIds()) {
            idValidator.isId(groupId, errors, "group id");
        }

        dateValidator.isDate(request.getDate(), errors, "date");

        return errors;
    }
}
