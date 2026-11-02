package org.example.repository.lesson_repository;

import org.example.entity.LessonEntity;

import java.util.List;

public interface ILessonRepository {
    long create(LessonEntity lesson);
    LessonEntity getById(long id);

    List<LessonEntity> getAll(String startDate, String endDate);
    List<LessonEntity> getLessonsByGroup(String startDate, String endDate, long groupId);
    List<LessonEntity> getLessonsByTeacher(String startDate, String endDate, long teacherId);

    void update(LessonEntity lesson);
    void deleteById(long id);
}
