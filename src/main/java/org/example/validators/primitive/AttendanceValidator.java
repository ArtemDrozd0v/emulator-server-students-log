package org.example.validators.primitive;

import org.example.model.AttendanceStatus;

import java.util.List;

public class AttendanceValidator {
    public List<String> isStatus(String status, List<String> errors, String fieldName) {
        try {
            AttendanceStatus.valueOf(status.trim().toUpperCase());
        } catch(Exception e) {
            errors.add(String.format("%s: should be correct status", fieldName));
        }
        return errors;
    }
}
