package org.example.converter.student_converter;

import org.example.entity.StudentEntity;
import org.example.response.GroupResponse;
import org.example.response.StudentResponse;

public class StudentEntityConverter {
    public StudentResponse toResponse(StudentEntity student, String groupName) {
        return new StudentResponse(
                student.getId(),
                student.getLastName(),
                student.getFirstName(),
                student.getMiddleName(),
                new GroupResponse(student.getGroupId(), groupName),
                student.getStatus()
        );
    }

}
