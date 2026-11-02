package org.example.validators.request;

import org.example.request.IdRequest;
import org.example.validators.primitive.IdValidator;

import java.util.ArrayList;
import java.util.List;

public class IdRequestValidator implements RequestValidator<IdRequest> {
    private final IdValidator idValidator;

    public IdRequestValidator(IdValidator idValidator) {
        this.idValidator = idValidator;
    }

    @Override
    public List<String> validate(IdRequest request) {
        List<String> errors = new ArrayList<>();

        idValidator.isId(request.getId(), errors, "id");

        return errors;
    }
}
