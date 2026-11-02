package org.example.validators.request.attendance_validator;

import org.example.request.attendance_request.EditAttendanceRequest;
import org.example.validators.primitive.AttendanceValidator;
import org.example.validators.primitive.IdValidator;
import org.example.validators.request.RequestValidator;

import java.util.ArrayList;
import java.util.List;

public class EditAttendanceValidator implements RequestValidator<EditAttendanceRequest> {
    private final IdValidator idValidator;
    private final AttendanceValidator attendanceValidator;

    public EditAttendanceValidator(IdValidator idValidator, AttendanceValidator attendanceValidator) {
        this.idValidator = idValidator;
        this.attendanceValidator = attendanceValidator;
    }

    @Override
    public List<String> validate(EditAttendanceRequest request) {
        List<String> errors = new ArrayList<>();

        idValidator.isId(request.getId(), errors, "id");
        idValidator.isId(request.getLessonId(), errors, "lesson id");
        idValidator.isId(request.getStudentId(), errors, "student id");
        attendanceValidator.isStatus(request.getStatus(), errors, "status");

        return errors;
    }
}
