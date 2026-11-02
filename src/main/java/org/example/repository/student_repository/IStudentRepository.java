package org.example.repository.student_repository;

import org.example.entity.StudentEntity;

import java.util.List;

public interface IStudentRepository {
    long create(StudentEntity student);
    StudentEntity getById(long id);

    List<StudentEntity> getAll();
    List<StudentEntity> getByGroupId(long groupId);
    List<StudentEntity> getByStatus(String status);

    void update(StudentEntity student);
    void deleteById(long id);
}
