package org.example.validators.primitive;

import java.util.List;
import java.util.regex.Pattern;

public class DateValidator {
    private final Pattern DATE_PATTERN = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");

    public List<String> isDate(String date, List<String> errors, String fieldName) {
        if (date == null || !DATE_PATTERN.matcher(date).matches()) {
            errors.add(String.format("%s: invalid date format, expected yyyy-mm-dd", fieldName));
        }
        return errors;
    }
}
