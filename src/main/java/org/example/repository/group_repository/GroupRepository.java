package org.example.repository.group_repository;

import org.example.data_base.DataBase;
import org.example.entity.GroupEntity;

import java.util.List;

public class GroupRepository implements IGroupRepository {
    private final DataBase dataBase;

    public GroupRepository(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public long create(GroupEntity group) {
        long id = dataBase.nextGroupId();

        GroupEntity savedGroup = new GroupEntity(
                id,
                group.getName(),
                group.getCreatedAt(),
                group.getUpdatedAt()
        );

        dataBase.getGroups().put(id, savedGroup);
        return id;
    }

    @Override
    public GroupEntity getById(long id) {
        return dataBase.getGroups().get(id);
    }

    @Override
    public GroupEntity getByName(String name) {
        return dataBase.getGroups().values().stream()
                .filter(group -> group.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<GroupEntity> getAll() {
        return dataBase.getGroups().values().stream().toList();
    }

    @Override
    public void update(GroupEntity group) {
        dataBase.getGroups().put(group.getId(), group);
    }

    @Override
    public void deleteById(long id) {
        dataBase.getGroups().remove(id);
    }
}
