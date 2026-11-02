package org.example.service;

import org.example.converter.teacher_converter.TeacherEntityConverter;
import org.example.converter.teacher_converter.TeacherRequestConverter;
import org.example.entity.TeacherEntity;
import org.example.exception.ServerErrors;
import org.example.exception.ServerException;
import org.example.repository.teacher_repository.ITeacherRepository;
import org.example.request.IdRequest;
import org.example.request.teacher_request.AddTeacherRequest;
import org.example.request.teacher_request.EditTeacherRequest;
import org.example.response.*;
import org.example.validators.request.IdRequestValidator;
import org.example.validators.request.teacher_validator.AddTeacherValidator;
import org.example.validators.request.teacher_validator.EditTeacherValidator;

import java.util.List;

public class TeacherService {
    private final ITeacherRepository teacherRepository;
    private final AddTeacherValidator addTeacherValidator;
    private final EditTeacherValidator editTeacherValidator;
    private final TeacherEntityConverter teacherEntityConverter;
    private final TeacherRequestConverter teacherRequestConverter;
    private final IdRequestValidator idRequestValidator;

    public TeacherService(ITeacherRepository teacherRepository, AddTeacherValidator addTeacherValidator, EditTeacherValidator editTeacherValidator, TeacherEntityConverter teacherEntityConverter, TeacherRequestConverter teacherRequestConverter, IdRequestValidator idRequestValidator) {
        this.idRequestValidator = idRequestValidator;
        this.teacherRepository = teacherRepository;
        this.addTeacherValidator = addTeacherValidator;
        this.editTeacherValidator = editTeacherValidator;
        this.teacherEntityConverter = teacherEntityConverter;
        this.teacherRequestConverter = teacherRequestConverter;
    }

    public IdResponse addTeacher(AddTeacherRequest request) throws ServerException {
        List<String> errors = addTeacherValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on adding teacher", ServerErrors.TEACHER_VALIDATION_ERROR.getCode(), errors);
        }
        TeacherEntity teacherEntity = teacherRequestConverter.toEntity(request);
        try {
            long id = teacherRepository.create(teacherEntity);
            return new IdResponse(id);
        } catch (Exception e) {
            throw new ServerException("Unable to add teacher: DB error", ServerErrors.TEACHER_ADD_ERROR.getCode(), null);
        }
    }

    public Void editTeacher(EditTeacherRequest request) throws ServerException {
        List<String> errors = editTeacherValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on editing teacher", ServerErrors.TEACHER_VALIDATION_ERROR.getCode(), errors);
        }
        try {
            TeacherEntity existingTeacher = teacherRepository.getById(request.getId());
            TeacherEntity teacherEntity = teacherRequestConverter.toEntity(request, existingTeacher.getCreatedAt());
            teacherRepository.update(teacherEntity);
        } catch (Exception e) {
            throw new ServerException("Unable to edit teacher: DB error", ServerErrors.TEACHER_EDIT_ERROR.getCode(), null);
        }
        return null;
    }

    public Void deleteTeacher(IdRequest request) throws ServerException {
        List<String> errors = idRequestValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on deleting teacher", ServerErrors.TEACHER_VALIDATION_ERROR.getCode(), errors);
        }
        try {
            teacherRepository.deleteById(request.getId());
        } catch (Exception e) {
            throw new ServerException("Unable to delete teacher: DB error", ServerErrors.TEACHER_DELETE_ERROR.getCode(), null);
        }
        return null;
    }

    public List<TeacherResponse> getTeachers() throws ServerException {
        try {
            return teacherRepository.getAll().stream()
                    .map(teacherEntityConverter::toResponse)
                    .toList();
        } catch (Exception e) {
            throw new ServerException("Unable to get teacher: DB error", ServerErrors.TEACHER_GET_ERROR.getCode(), null);
        }
    }

    public TeacherResponse getTeacherById(IdRequest request) throws ServerException {
        List<String> errors = idRequestValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on getting teacher", ServerErrors.TEACHER_VALIDATION_ERROR.getCode(), errors);
        }
        try {
            TeacherEntity teacherEntity = teacherRepository.getById(request.getId());
            return teacherEntityConverter.toResponse(teacherEntity);
        } catch (Exception e) {
            throw new ServerException("Unable to get teacher: DB error", ServerErrors.TEACHER_GET_ERROR.getCode(), null);
        }
    }
}
