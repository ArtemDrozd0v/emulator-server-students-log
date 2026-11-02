package org.example.repository.teacher_repository;

import org.example.data_base.DataBase;
import org.example.entity.TeacherEntity;

import java.util.List;

public class TeacherRepository implements ITeacherRepository {
    private final DataBase dataBase;

    public TeacherRepository(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public long create(TeacherEntity teacher) {
        long id = dataBase.nextTeacherId();

        TeacherEntity savedTeacher = new TeacherEntity(
                id,
                teacher.getLastName(),
                teacher.getFirstName(),
                teacher.getMiddleName(),
                teacher.getCreatedAt(),
                teacher.getUpdatedAt()
        );

        dataBase.getTeachers().put(id, savedTeacher);
        return id;
    }

    @Override
    public TeacherEntity getById(long id) {
        return dataBase.getTeachers().get(id);
    }

    @Override
    public List<TeacherEntity> getAll() {
        return dataBase.getTeachers().values().stream().toList();
    }

    @Override
    public void update(TeacherEntity teacher) {
        dataBase.getTeachers().put(teacher.getId(), teacher);
    }

    @Override
    public void deleteById(long id) {
        dataBase.getTeachers().remove(id);
    }
}
