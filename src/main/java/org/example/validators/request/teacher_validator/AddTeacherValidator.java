package org.example.validators.request.teacher_validator;

import org.example.request.teacher_request.AddTeacherRequest;
import org.example.validators.primitive.NameValidator;
import org.example.validators.request.RequestValidator;

import java.util.ArrayList;
import java.util.List;

public class AddTeacherValidator implements RequestValidator<AddTeacherRequest> {
    private final int maxLen = 100;
    private final NameValidator nameValidator;

    public AddTeacherValidator(NameValidator nameValidator) {
        this.nameValidator = nameValidator;
    }

    @Override
    public List<String> validate(AddTeacherRequest request) {
        List<String> errors = new ArrayList<>();

        nameValidator.isNotEmpty(request.getMiddleName(), errors, "middleName");
        nameValidator.isNotEmpty(request.getFirstName(), errors, "firstName");
        nameValidator.isNotEmpty(request.getLastName(), errors, "lastName");

        nameValidator.isNotLong(request.getMiddleName(), errors, "middleName", maxLen);
        nameValidator.isNotLong(request.getFirstName(), errors, "firstName", maxLen);
        nameValidator.isNotLong(request.getLastName(), errors, "lastName", maxLen);


        return errors;
    }
}
