package org.example.repository.group_repository;

import org.example.entity.GroupEntity;

import java.util.List;

public interface IGroupRepository {
    long create(GroupEntity group);
    GroupEntity getById(long id);
    GroupEntity getByName(String name);

    List<GroupEntity> getAll();

    void update(GroupEntity group);
    void deleteById(long id);
}
