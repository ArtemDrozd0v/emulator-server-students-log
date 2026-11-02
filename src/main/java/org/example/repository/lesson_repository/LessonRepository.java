package org.example.repository.lesson_repository;

import org.example.data_base.DataBase;
import org.example.entity.LessonEntity;

import java.util.Comparator;
import java.util.List;

public class LessonRepository implements ILessonRepository {
    private final DataBase dataBase;

    public LessonRepository(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public long create(LessonEntity lesson) {
        long id = dataBase.nextLessonId();

        LessonEntity savedLesson = new LessonEntity(
                id,
                lesson.getSubjectId(),
                lesson.getTeacherId(),
                List.copyOf(lesson.getGroupsId()),
                lesson.getDate(),
                lesson.getLessonNumber(),
                lesson.getCreatedAt(),
                lesson.getUpdatedAt()
        );

        dataBase.getLessons().put(id, savedLesson);
        return id;
    }

    @Override
    public LessonEntity getById(long id) {
        return dataBase.getLessons().get(id);
    }

    @Override
    public List<LessonEntity> getAll(String startDate, String endDate) {
        return dataBase.getLessons().values().stream()
                .filter(lesson -> lesson.getDate().compareTo(startDate) >= 0
                        && lesson.getDate().compareTo(endDate) <= 0)
                .sorted(Comparator.comparing(LessonEntity::getDate)
                        .thenComparingInt(LessonEntity::getLessonNumber))
                .toList();
    }

    @Override
    public List<LessonEntity> getLessonsByGroup(String startDate, String endDate, long groupId) {
        return dataBase.getLessons().values().stream()
                .filter(lesson -> lesson.getDate().compareTo(startDate) >= 0
                        && lesson.getDate().compareTo(endDate) <= 0)
                .filter(lesson -> lesson.getGroupsId().contains(groupId))
                .sorted(Comparator.comparing(LessonEntity::getDate)
                        .thenComparingInt(LessonEntity::getLessonNumber))
                .toList();
    }

    @Override
    public List<LessonEntity> getLessonsByTeacher(String startDate, String endDate, long teacherId) {
        return dataBase.getLessons().values().stream()
                .filter(lesson -> lesson.getDate().compareTo(startDate) >= 0
                        && lesson.getDate().compareTo(endDate) <= 0)
                .filter(lesson -> lesson.getTeacherId() == teacherId)
                .sorted(Comparator.comparing(LessonEntity::getDate)
                        .thenComparingInt(LessonEntity::getLessonNumber))
                .toList();
    }

    @Override
    public void update(LessonEntity lesson) {
        dataBase.getLessons().put(lesson.getId(), lesson);
    }

    @Override
    public void deleteById(long id) {
        dataBase.getLessons().remove(id);
    }
}
