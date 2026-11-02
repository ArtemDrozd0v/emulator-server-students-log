package org.example.service;

import org.example.converter.student_converter.StudentEntityConverter;
import org.example.converter.student_converter.StudentRequestConverter;
import org.example.entity.StudentEntity;
import org.example.exception.ServerErrors;
import org.example.exception.ServerException;
import org.example.repository.group_repository.IGroupRepository;
import org.example.repository.student_repository.IStudentRepository;
import org.example.request.IdRequest;
import org.example.request.student_request.AddStudentRequest;
import org.example.request.student_request.EditStudentRequest;
import org.example.response.*;
import org.example.validators.primitive.IdValidator;
import org.example.validators.request.IdRequestValidator;
import org.example.validators.request.student_validator.AddStudentValidator;
import org.example.validators.request.student_validator.EditStudentValidator;

import java.util.List;

public class StudentService {
    private final IStudentRepository studentRepository;
    private final IGroupRepository groupRepository;
    private final AddStudentValidator addStudentValidator;
    private final EditStudentValidator editStudentValidator;
    private final StudentEntityConverter studentEntityConverter;
    private final StudentRequestConverter studentRequestConverter;
    private final IdRequestValidator idRequestValidator;

    public StudentService(IStudentRepository studentRepository, IGroupRepository groupRepository, AddStudentValidator addStudentValidator, EditStudentValidator editStudentValidator, StudentEntityConverter studentEntityConverter, StudentRequestConverter studentRequestConverter, IdRequestValidator idRequestValidator) {
        this.idRequestValidator = idRequestValidator;
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.addStudentValidator = addStudentValidator;
        this.editStudentValidator = editStudentValidator;
        this.studentEntityConverter = studentEntityConverter;
        this.studentRequestConverter = studentRequestConverter;
    }

    public IdResponse addStudent(AddStudentRequest request) throws ServerException {
        List<String> errors = addStudentValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on adding student", ServerErrors.STUDENT_VALIDATION_ERROR.getCode(), errors);
        }
        StudentEntity studentEntity = studentRequestConverter.toEntity(request);
        try {
            long id = studentRepository.create(studentEntity);
            return new IdResponse(id);
        } catch (Exception e) {
            throw new ServerException("Unable to add student: DB error", ServerErrors.STUDENT_ADD_ERROR.getCode(), null);
        }
    }

    public Void editStudent(EditStudentRequest request) throws ServerException {
        List<String> errors = editStudentValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on editing student", ServerErrors.STUDENT_VALIDATION_ERROR.getCode(), errors);
        }
        try {
            StudentEntity existingStudent = studentRepository.getById(request.getId());
            StudentEntity studentEntity = studentRequestConverter.toEntity(request, existingStudent.getCreatedAt());
            studentRepository.update(studentEntity);
        } catch (Exception e) {
            throw new ServerException("Unable to edit student: DB error", ServerErrors.STUDENT_EDIT_ERROR.getCode(), null);
        }
        return null;
    }

    public Void deleteStudent(IdRequest request) throws ServerException {
        List<String> errors = idRequestValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on deleting student", ServerErrors.STUDENT_VALIDATION_ERROR.getCode(), errors);
        }
        try {
            studentRepository.deleteById(request.getId());
        } catch (Exception e) {
            throw new ServerException("Unable to delete student: DB error", ServerErrors.STUDENT_DELETE_ERROR.getCode(), null);
        }
        return null;
    }

    public List<StudentResponse> getStudents() throws ServerException {
        try {
            return studentRepository.getAll().stream()
                    .map(studentEntity -> studentEntityConverter.toResponse(studentEntity, groupRepository.getById(studentEntity.getGroupId()).getName()))
                    .toList();
        } catch (Exception e) {
            throw new ServerException("Unable to get student: DB error", ServerErrors.STUDENT_GET_ERROR.getCode(), null);
        }
    }

    public StudentResponse getStudentById(IdRequest request) throws ServerException {
        List<String> errors = idRequestValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on getting student", ServerErrors.STUDENT_VALIDATION_ERROR.getCode(), errors);
        }
        try {
            StudentEntity studentEntity = studentRepository.getById(request.getId());
            return studentEntityConverter.toResponse(studentEntity, groupRepository.getById(studentEntity.getGroupId()).getName());
        } catch (Exception e) {
            throw new ServerException("Unable to get student: DB error", ServerErrors.STUDENT_GET_ERROR.getCode(), null);
        }
    }

    public List<StudentResponse> getStudentsByGroup(IdRequest request) throws ServerException {
        List<String> errors = idRequestValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on getting student", ServerErrors.STUDENT_VALIDATION_ERROR.getCode(), errors);
        }
        try {
            return studentRepository.getByGroupId(request.getId()).stream()
                    .map(studentEntity -> studentEntityConverter.toResponse(studentEntity, groupRepository.getById(studentEntity.getGroupId()).getName()))
                    .toList();
        } catch (Exception e) {
            throw new ServerException("Unable to get student: DB error", ServerErrors.STUDENT_GET_ERROR.getCode(), null);
        }
    }

}
