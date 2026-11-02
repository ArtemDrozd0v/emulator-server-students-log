package org.example.repository.student_repository;

import org.example.data_base.DataBase;
import org.example.entity.StudentEntity;
import org.example.model.StudentStatus;

import java.util.List;

public class StudentRepository implements IStudentRepository {
    private final DataBase dataBase;

    public StudentRepository(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public long create(StudentEntity student) {
        long id = dataBase.nextStudentId();

        StudentEntity newStudent = new StudentEntity(
                id,
                student.getLastName(),
                student.getFirstName(),
                student.getMiddleName(),
                student.getGroupId(),
                student.getStatus(),
                student.getCreatedAt(),
                student.getUpdatedAt()
        );

        dataBase.getStudents().put(id, newStudent);
        return id;
    }

    @Override
    public StudentEntity getById(long id) {
        return dataBase.getStudents().get(id);
    }

    @Override
    public List<StudentEntity> getAll() {
        return dataBase.getStudents().values().stream().toList();
    }

    @Override
    public List<StudentEntity> getByGroupId(long groupId) {
        return dataBase.getStudents()
                .values().stream()
                .filter(student -> student.getGroupId() == groupId)
                .toList();
    }

    @Override
    public List<StudentEntity> getByStatus(String status) {
        return dataBase.getStudents().values().stream()
                .filter(student -> student.getStatus().equals(StudentStatus.valueOf(status.trim().toUpperCase())))
                .toList();
    }

    @Override
    public void update(StudentEntity student) {
        dataBase.getStudents().put(student.getId(), student);
    }

    @Override
    public void deleteById(long id) {
        dataBase.getStudents().remove(id);
    }
}
