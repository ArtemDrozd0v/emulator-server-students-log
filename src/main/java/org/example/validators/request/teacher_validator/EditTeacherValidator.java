package org.example.validators.request.teacher_validator;

import org.example.request.teacher_request.EditTeacherRequest;
import org.example.validators.primitive.IdValidator;
import org.example.validators.primitive.NameValidator;
import org.example.validators.request.RequestValidator;

import java.util.ArrayList;
import java.util.List;

public class EditTeacherValidator implements RequestValidator<EditTeacherRequest> {
    private final int maxLen = 100;
    private final NameValidator nameValidator;
    private final IdValidator idValidator;

    public EditTeacherValidator(NameValidator nameValidator, IdValidator idValidator) {
        this.nameValidator = nameValidator;
        this.idValidator = idValidator;
    }

    @Override
    public List<String> validate(EditTeacherRequest request) {
        List<String> errors = new ArrayList<>();

        nameValidator.isNotEmpty(request.getMiddleName(), errors, "middleName");
        nameValidator.isNotEmpty(request.getFirstName(), errors, "firstName");
        nameValidator.isNotEmpty(request.getLastName(), errors, "lastName");

        nameValidator.isNotLong(request.getMiddleName(), errors, "middleName", maxLen);
        nameValidator.isNotLong(request.getFirstName(), errors, "firstName", maxLen);
        nameValidator.isNotLong(request.getLastName(), errors, "lastName", maxLen);

        idValidator.isId(request.getId(), errors, "id");

        return errors;
    }

}
