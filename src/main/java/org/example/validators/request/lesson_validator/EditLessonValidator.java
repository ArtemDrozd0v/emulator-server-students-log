package org.example.validators.request.lesson_validator;

import org.example.request.lesson_request.EditLessonRequest;
import org.example.validators.primitive.IdValidator;
import org.example.validators.primitive.DateValidator;
import org.example.validators.request.RequestValidator;

import java.util.ArrayList;
import java.util.List;

public class EditLessonValidator implements RequestValidator<EditLessonRequest> {
    private final IdValidator idValidator;
    private final DateValidator dateValidator;

    public EditLessonValidator(IdValidator idValidator, DateValidator dateValidator) {
        this.idValidator = idValidator;
        this.dateValidator = dateValidator;
    }

    @Override
    public List<String> validate(EditLessonRequest request) {
        List<String> errors = new ArrayList<>();

        idValidator.isId(request.getId(), errors, "id");

        idValidator.isId(request.getSubjectId(), errors, "subject id");

        idValidator.isId(request.getTeacherId(), errors, "teacher id");

        for (Long groupId : request.getGroupIds()) {
            idValidator.isId(groupId, errors, "group id");
        }

        dateValidator.isDate(request.getDate(), errors, "date");

        return errors;
    }
}
