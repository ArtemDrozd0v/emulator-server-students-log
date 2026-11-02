package org.example.validators.primitive;

import org.example.model.StudentStatus;

import java.util.List;

public class StatusValidator {
    public List<String> isStatus(String status, List<String> errors, String fieldName) {
        try {
            StudentStatus.valueOf(status.trim().toUpperCase());
        } catch(Exception e) {
            errors.add(String.format("%s: should be correct status", fieldName));
        }
        return errors;
    }
}
