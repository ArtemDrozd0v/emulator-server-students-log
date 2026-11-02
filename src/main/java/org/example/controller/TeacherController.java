package org.example.controller;

import org.example.exception.ValidationsException;
import org.example.service.TeacherService;
import org.example.exception.ServerException;
import org.example.request.IdRequest;
import org.example.request.teacher_request.AddTeacherRequest;
import org.example.request.teacher_request.EditTeacherRequest;
import org.example.response.*;

import java.util.List;

public class TeacherController {
    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    public ResponseEntity<CommonResponse<IdResponse>> addTeacher(AddTeacherRequest request) {
        try {
            return new ResponseEntity<>(HttpStatus.CREATED, new CommonResponse<>(service.addTeacher(request)));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }

    public ResponseEntity<CommonResponse<Void>> editTeacher(EditTeacherRequest request) {
        try {
            return new ResponseEntity<>(HttpStatus.OK, new CommonResponse<>(service.editTeacher(request)));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }

    public ResponseEntity<CommonResponse<Void>> deleteTeacher(IdRequest request) {
        try {
            return new ResponseEntity<>(HttpStatus.OK, new CommonResponse<>(service.deleteTeacher(request)));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }

    public ResponseEntity<CommonResponse<List<TeacherResponse>>> getTeachers() {
        try {
            return new ResponseEntity<>(HttpStatus.OK, new CommonResponse<>(service.getTeachers()));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }

    public ResponseEntity<CommonResponse<TeacherResponse>> getTeacherById(IdRequest request) {
        try {
            return new ResponseEntity<>(HttpStatus.OK, new CommonResponse<>(service.getTeacherById(request)));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }
}
