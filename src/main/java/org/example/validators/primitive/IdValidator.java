package org.example.validators.primitive;

import java.util.List;

public class IdValidator {
    public List<String> isId(long id, List<String> errors, String fieldName) {
        if (id <= 0) {
            errors.add(String.format("%s: should be positive", fieldName));
        }
        return errors;
    }
}
