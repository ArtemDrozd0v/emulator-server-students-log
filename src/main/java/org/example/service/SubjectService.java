package org.example.service;

import org.example.converter.subject_converter.SubjectEntityConverter;
import org.example.converter.subject_converter.SubjectRequestConverter;
import org.example.entity.SubjectEntity;
import org.example.exception.ServerErrors;
import org.example.exception.ServerException;
import org.example.repository.subject_repository.ISubjectRepository;
import org.example.request.IdRequest;
import org.example.request.subject_request.AddSubjectRequest;
import org.example.request.subject_request.EditSubjectRequest;
import org.example.response.*;
import org.example.validators.request.IdRequestValidator;
import org.example.validators.request.subject_validator.AddSubjectValidator;
import org.example.validators.request.subject_validator.EditSubjectValidator;

import java.util.List;

public class SubjectService {
    private final ISubjectRepository subjectRepository;
    private final AddSubjectValidator addSubjectValidator;
    private final EditSubjectValidator editSubjectValidator;
    private final SubjectEntityConverter subjectEntityConverter;
    private final SubjectRequestConverter subjectRequestConverter;
    private final IdRequestValidator idRequestValidator;

    public SubjectService(ISubjectRepository subjectRepository, AddSubjectValidator addSubjectValidator, EditSubjectValidator editSubjectValidator, SubjectEntityConverter subjectEntityConverter, SubjectRequestConverter subjectRequestConverter, IdRequestValidator idRequestValidator) {
        this.idRequestValidator = idRequestValidator;
        this.subjectRepository = subjectRepository;
        this.addSubjectValidator = addSubjectValidator;
        this.editSubjectValidator = editSubjectValidator;
        this.subjectEntityConverter = subjectEntityConverter;
        this.subjectRequestConverter = subjectRequestConverter;
    }

    public IdResponse addSubject(AddSubjectRequest request) throws ServerException {
        List<String> errors = addSubjectValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on adding subject", ServerErrors.SUBJECT_VALIDATION_ERROR.getCode(), errors);
        }
        SubjectEntity subjectEntity = subjectRequestConverter.toEntity(request);
        try {
            long id = subjectRepository.create(subjectEntity);
            return new IdResponse(id);
        } catch (Exception e) {
            throw new ServerException("Unable to add subject: DB error", ServerErrors.SUBJECT_ADD_ERROR.getCode(), null);
        }
    }

    public Void editSubject(EditSubjectRequest request) throws ServerException {
        List<String> errors = editSubjectValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on editing subject", ServerErrors.SUBJECT_VALIDATION_ERROR.getCode(), errors);
        }
        try {
            SubjectEntity existingSubject = subjectRepository.getById(request.getId());
            SubjectEntity subjectEntity = subjectRequestConverter.toEntity(request, existingSubject.getCreatedAt());
            subjectRepository.update(subjectEntity);
        } catch (Exception e) {
            throw new ServerException("Unable to edit subject: DB error", ServerErrors.SUBJECT_EDIT_ERROR.getCode(), null);
        }
        return null;
    }

    public Void deleteSubject(IdRequest request) throws ServerException {
        List<String> errors = idRequestValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on deleting subject", ServerErrors.SUBJECT_VALIDATION_ERROR.getCode(), errors);
        }
        try {
            subjectRepository.deleteById(request.getId());
        } catch (Exception e) {
            throw new ServerException("Unable to delete subject: DB error", ServerErrors.SUBJECT_DELETE_ERROR.getCode(), null);
        }
        return null;
    }

    public List<SubjectResponse> getSubjects() throws ServerException {
        try {
            return subjectRepository.getAll().stream()
                    .map(subjectEntityConverter::toResponse)
                    .toList();
        } catch (Exception e) {
            throw new ServerException("Unable to get subject: DB error", ServerErrors.SUBJECT_GET_ERROR.getCode(), null);
        }
    }

    public SubjectResponse getSubjectById(IdRequest request) throws ServerException {
        List<String> errors = idRequestValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on getting subject", ServerErrors.SUBJECT_VALIDATION_ERROR.getCode(), errors);
        }
        try {
            SubjectEntity subjectEntity = subjectRepository.getById(request.getId());
            return subjectEntityConverter.toResponse(subjectEntity);
        } catch (Exception e) {
            throw new ServerException("Unable to get subject: DB error", ServerErrors.SUBJECT_GET_ERROR.getCode(), null);
        }
    }
}
