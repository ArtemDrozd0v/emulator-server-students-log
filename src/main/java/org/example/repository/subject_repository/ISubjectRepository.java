package org.example.repository.subject_repository;

import org.example.entity.SubjectEntity;

import java.util.List;

public interface ISubjectRepository {
    long create(SubjectEntity subject);
    SubjectEntity getById(long id);

    List<SubjectEntity> getAll();

    void update(SubjectEntity subject);
    void deleteById(long id);
}
