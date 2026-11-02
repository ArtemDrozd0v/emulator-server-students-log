package org.example.validators.request.student_validator;

import org.example.request.student_request.EditStudentRequest;
import org.example.validators.primitive.IdValidator;
import org.example.validators.primitive.NameValidator;
import org.example.validators.primitive.StatusValidator;
import org.example.validators.request.RequestValidator;

import java.util.ArrayList;
import java.util.List;

public class EditStudentValidator implements RequestValidator<EditStudentRequest> {
    private final int maxLen = 100;
    private final NameValidator nameValidator;
    private final IdValidator idValidator;
    private final StatusValidator statusValidator;

    public EditStudentValidator(NameValidator nameValidator, IdValidator idValidator, StatusValidator statusValidator) {
        this.nameValidator = nameValidator;
        this.idValidator = idValidator;
        this.statusValidator = statusValidator;
    }

    @Override
    public List<String> validate(EditStudentRequest request) {
        List<String> errors = new ArrayList<>();

        nameValidator.isNotEmpty(request.getMiddleName(), errors, "middleName");
        nameValidator.isNotEmpty(request.getFirstName(), errors, "firstName");
        nameValidator.isNotEmpty(request.getLastName(), errors, "lastName");

        nameValidator.isNotLong(request.getMiddleName(), errors, "middleName", maxLen);
        nameValidator.isNotLong(request.getFirstName(), errors, "firstName", maxLen);
        nameValidator.isNotLong(request.getLastName(), errors, "lastName", maxLen);

        idValidator.isId(request.getId(), errors, "id");
        idValidator.isId(request.getGroupId(), errors, "groupId");

        statusValidator.isStatus(request.getStatus(), errors, "status");

        return errors;
    }
}
