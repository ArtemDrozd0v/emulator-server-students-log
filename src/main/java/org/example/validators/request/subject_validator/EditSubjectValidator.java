package org.example.validators.request.subject_validator;

import org.example.request.subject_request.EditSubjectRequest;
import org.example.validators.primitive.IdValidator;
import org.example.validators.primitive.NameValidator;
import org.example.validators.request.RequestValidator;

import java.util.ArrayList;
import java.util.List;

public class EditSubjectValidator implements RequestValidator<EditSubjectRequest> {
    private final int maxLen = 100;
    private final NameValidator nameValidator;
    private final IdValidator idValidator;

    public EditSubjectValidator(NameValidator nameValidator, IdValidator idValidator) {
        this.nameValidator = nameValidator;
        this.idValidator = idValidator;
    }

    @Override
    public List<String> validate(EditSubjectRequest request) {
        List<String> errors = new ArrayList<>();

        nameValidator.isNotEmpty(request.getName(), errors, "name");
        nameValidator.isNotLong(request.getName(), errors, "name", maxLen);

        idValidator.isId(request.getId(), errors, "id");

        return errors;
    }
}
