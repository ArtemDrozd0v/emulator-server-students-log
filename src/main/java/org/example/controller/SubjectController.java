package org.example.controller;

import org.example.exception.ValidationsException;
import org.example.service.SubjectService;
import org.example.exception.ServerException;
import org.example.request.IdRequest;
import org.example.request.subject_request.AddSubjectRequest;
import org.example.request.subject_request.EditSubjectRequest;
import org.example.response.*;

import java.util.List;

public class SubjectController {
    private final SubjectService service;

    public SubjectController(SubjectService service) {
        this.service = service;
    }

    public ResponseEntity<CommonResponse<IdResponse>> addSubject(AddSubjectRequest request) {
        try {
            return new ResponseEntity<>(HttpStatus.CREATED, new CommonResponse<>(service.addSubject(request)));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }

    public ResponseEntity<CommonResponse<Void>> editSubject(EditSubjectRequest request) {
        try {
            return new ResponseEntity<>(HttpStatus.OK, new CommonResponse<>(service.editSubject(request)));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }

    public ResponseEntity<CommonResponse<Void>> deleteSubject(IdRequest request) {
        try {
            return new ResponseEntity<>(HttpStatus.OK, new CommonResponse<>(service.deleteSubject(request)));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }

    public ResponseEntity<CommonResponse<List<SubjectResponse>>> getSubjects() {
        try {
            return new ResponseEntity<>(HttpStatus.OK, new CommonResponse<>(service.getSubjects()));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }

    public ResponseEntity<CommonResponse<SubjectResponse>> getSubjectById(IdRequest request) {
        try {
            return new ResponseEntity<>(HttpStatus.OK, new CommonResponse<>(service.getSubjectById(request)));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }
}
