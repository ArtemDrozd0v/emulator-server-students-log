package org.example.converter.group_converter;

import org.example.entity.GroupEntity;
import org.example.response.GroupResponse;

public class GroupEntityConverter {
    public GroupResponse toResponse(GroupEntity group) {
        return new GroupResponse(
                group.getId(),
                group.getName()
        );
    }

}
