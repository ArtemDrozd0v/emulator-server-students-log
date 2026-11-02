package org.example.converter.group_converter;

import org.example.entity.GroupEntity;
import org.example.request.group_request.AddGroupRequest;
import org.example.request.group_request.EditGroupRequest;

import java.time.Instant;

public class GroupRequestConverter {
    public GroupEntity toEntity(AddGroupRequest request) {
        Instant now = Instant.now();
        long time = now.toEpochMilli();

        return new GroupEntity(
                null,
                request.getName(),
                time,
                time
        );
    }

    public GroupEntity toEntity(EditGroupRequest request, long createdAt) {
        Instant now = Instant.now();
        long time = now.toEpochMilli();

        return new GroupEntity(
                request.getId(),
                request.getName(),
                createdAt,
                time
        );
    }

}
