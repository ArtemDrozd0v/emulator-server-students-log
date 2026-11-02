package org.example.repository.subject_repository;

import org.example.data_base.DataBase;
import org.example.entity.SubjectEntity;

import java.util.List;

public class SubjectRepository implements ISubjectRepository {
    private final DataBase dataBase;

    public SubjectRepository(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public long create(SubjectEntity subject) {
        long id = dataBase.nextSubjectId();

        SubjectEntity savedSubject = new SubjectEntity(
                id,
                subject.getName(),
                subject.getCreatedAt(),
                subject.getUpdatedAt()
        );

        dataBase.getSubjects().put(id, savedSubject);
        return id;
    }

    @Override
    public SubjectEntity getById(long id) {
        return dataBase.getSubjects().get(id);
    }

    @Override
    public List<SubjectEntity> getAll() {
        return dataBase.getSubjects().values().stream().toList();
    }

    @Override
    public void update(SubjectEntity subject) {
        dataBase.getSubjects().put(subject.getId(), subject);
    }

    @Override
    public void deleteById(long id) {
        dataBase.getSubjects().remove(id);
    }
}
