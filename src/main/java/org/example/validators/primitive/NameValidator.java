package org.example.validators.primitive;

import java.util.List;

public class NameValidator {
    public List<String> isNotEmpty(String name, List<String> errors, String fieldName) {
        if (name == null || name.isEmpty()) {
            errors.add(String.format("%s: should be not empty", fieldName));
        }
        return errors;
    }

    public List<String> isNotLong(String name, List<String> errors, String fieldName, int maxLen) {
        if (name == null || maxLen < name.length()) {
            errors.add(String.format("%s: should be not longer than %d", fieldName, maxLen));
        }
        return errors;
    }
}
