package org.example.validators.request.group_validator;

import org.example.request.group_request.EditGroupRequest;
import org.example.validators.primitive.IdValidator;
import org.example.validators.primitive.NameValidator;
import org.example.validators.request.RequestValidator;

import java.util.ArrayList;
import java.util.List;

public class EditGroupValidator implements RequestValidator<EditGroupRequest> {
    private final int maxLen = 100;
    private final NameValidator nameValidator;
    private final IdValidator idValidator;

    public EditGroupValidator(NameValidator nameValidator, IdValidator idValidator) {
        this.nameValidator = nameValidator;
        this.idValidator = idValidator;
    }

    @Override
    public List<String> validate(EditGroupRequest request) {
        List<String> errors = new ArrayList<>();

        nameValidator.isNotEmpty(request.getName(), errors, "name");
        nameValidator.isNotLong(request.getName(), errors, "name", maxLen);

        idValidator.isId(request.getId(), errors, "id");

        return errors;
    }
}
