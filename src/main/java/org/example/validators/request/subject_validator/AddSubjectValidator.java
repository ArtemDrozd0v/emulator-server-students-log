package org.example.validators.request.subject_validator;

import org.example.request.subject_request.AddSubjectRequest;
import org.example.validators.primitive.NameValidator;
import org.example.validators.request.RequestValidator;

import java.util.ArrayList;
import java.util.List;

public class AddSubjectValidator implements RequestValidator<AddSubjectRequest> {
    private final int maxLen = 100;
    private final NameValidator nameValidator;

    public AddSubjectValidator(NameValidator nameValidator) {
        this.nameValidator = nameValidator;
    }

    @Override
    public List<String> validate(AddSubjectRequest request) {
        List<String> errors = new ArrayList<>();

        nameValidator.isNotEmpty(request.getName(), errors, "name");
        nameValidator.isNotLong(request.getName(), errors, "name", maxLen);

        return errors;
    }
}
