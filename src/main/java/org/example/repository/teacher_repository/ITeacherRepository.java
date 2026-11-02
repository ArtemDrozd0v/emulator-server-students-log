package org.example.repository.teacher_repository;

import org.example.entity.TeacherEntity;

import java.util.List;

public interface ITeacherRepository {
    long create(TeacherEntity teacher);
    TeacherEntity getById(long id);

    List<TeacherEntity> getAll();

    void update(TeacherEntity teacher);
    void deleteById(long id);
}
