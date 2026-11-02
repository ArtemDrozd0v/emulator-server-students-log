package org.example.service;

import org.example.converter.group_converter.GroupEntityConverter;
import org.example.converter.group_converter.GroupRequestConverter;
import org.example.entity.GroupEntity;
import org.example.exception.ServerErrors;
import org.example.exception.ServerException;
import org.example.repository.group_repository.IGroupRepository;
import org.example.request.IdRequest;
import org.example.request.group_request.AddGroupRequest;
import org.example.request.group_request.EditGroupRequest;
import org.example.response.GroupResponse;
import org.example.response.IdResponse;
import org.example.validators.primitive.IdValidator;
import org.example.validators.request.IdRequestValidator;
import org.example.validators.request.group_validator.AddGroupValidator;
import org.example.validators.request.group_validator.EditGroupValidator;

import java.util.List;

public class GroupService {
    private final IGroupRepository groupRepository;
    private final AddGroupValidator addGroupValidator;
    private final EditGroupValidator editGroupValidator;
    private final GroupEntityConverter groupEntityConverter;
    private final GroupRequestConverter groupRequestConverter;
    private final IdRequestValidator idRequestValidator;

    public GroupService(IGroupRepository groupRepository, AddGroupValidator addGroupValidator, EditGroupValidator editGroupValidator, GroupEntityConverter groupEntityConverter, GroupRequestConverter groupRequestConverter, IdRequestValidator idRequestValidator) {
        this.idRequestValidator = idRequestValidator;
        this.groupRepository = groupRepository;
        this.addGroupValidator = addGroupValidator;
        this.editGroupValidator = editGroupValidator;
        this.groupEntityConverter = groupEntityConverter;
        this.groupRequestConverter = groupRequestConverter;
    }

    public IdResponse addStudentGroup(AddGroupRequest request) throws ServerException {
        List<String> errors = addGroupValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on adding group", ServerErrors.GROUP_VALIDATION_ERROR.getCode(), errors);
        }
        GroupEntity groupEntity = groupRequestConverter.toEntity(request);
        try {
            long id = groupRepository.create(groupEntity);
            return new IdResponse(id);
        } catch (Exception e) {
            throw new ServerException("Unable to add group" + request + ": DB error", ServerErrors.GROUP_ADD_ERROR.getCode(), null);
        }
    }

    public Void editStudentGroup(EditGroupRequest request) throws ServerException {
        List<String> errors = editGroupValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on editing group", ServerErrors.GROUP_VALIDATION_ERROR.getCode(), errors);
        }
        try {
            GroupEntity existingGroup = groupRepository.getById(request.getId());
            GroupEntity groupEntity = groupRequestConverter.toEntity(request, existingGroup.getCreatedAt());
            groupRepository.update(groupEntity);
        } catch (Exception e) {
            throw new ServerException("Unable to edit group" + request + ": DB error", ServerErrors.GROUP_EDIT_ERROR.getCode(), null);
        }
        return null;
    }

    public Void deleteStudentGroup(IdRequest request) throws ServerException {
        List<String> errors = idRequestValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on deleting group", ServerErrors.GROUP_VALIDATION_ERROR.getCode(), errors);
        }
        try {
            groupRepository.deleteById(request.getId());
        } catch (Exception e) {
            throw new ServerException("Unable to delete group" + request + ": DB error", ServerErrors.GROUP_DELETE_ERROR.getCode(), null);
        }
        return null;
    }

    public List<GroupResponse> getStudentGroups() throws ServerException {
        try {
            return groupRepository.getAll().stream()
                    .map(groupEntityConverter::toResponse)
                    .toList();
        } catch (Exception e) {
            throw new ServerException("Unable to get groups: DB error", ServerErrors.GROUP_GET_ERROR.getCode(), null);
        }
    }

    public GroupResponse getStudentGroupById(IdRequest request) throws ServerException {
        List<String> errors = idRequestValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on getting group", ServerErrors.GROUP_VALIDATION_ERROR.getCode(), errors);
        }
        try {
            GroupEntity groupEntity = groupRepository.getById(request.getId());
            return groupEntityConverter.toResponse(groupEntity);
        } catch (Exception e) {
            throw new ServerException("Unable to get group" + request + ": DB error", ServerErrors.GROUP_GET_ERROR.getCode(), null);
        }
    }
}
