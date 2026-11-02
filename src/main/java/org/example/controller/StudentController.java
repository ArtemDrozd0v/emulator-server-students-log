package org.example.controller;

import org.example.exception.ValidationsException;
import org.example.service.StudentService;
import org.example.exception.ServerException;
import org.example.request.IdRequest;
import org.example.request.student_request.AddStudentRequest;
import org.example.request.student_request.EditStudentRequest;
import org.example.response.*;

import java.util.List;

public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    public ResponseEntity<CommonResponse<IdResponse>> addStudent(AddStudentRequest request) {
        try {
            return new ResponseEntity<>(HttpStatus.CREATED, new CommonResponse<>(service.addStudent(request)));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }

    public ResponseEntity<CommonResponse<Void>> editStudent(EditStudentRequest request) {
        try {
            return new ResponseEntity<>(HttpStatus.OK, new CommonResponse<>(service.editStudent(request)));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }

    public ResponseEntity<CommonResponse<Void>> deleteStudent(IdRequest request) {
        try {
            return new ResponseEntity<>(HttpStatus.OK, new CommonResponse<>(service.deleteStudent(request)));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }

    public ResponseEntity<CommonResponse<List<StudentResponse>>> getStudents() {
        try {
            return new ResponseEntity<>(HttpStatus.OK, new CommonResponse<>(service.getStudents()));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }

    public ResponseEntity<CommonResponse<StudentResponse>> getStudentById(IdRequest request) {
        try {
            return new ResponseEntity<>(HttpStatus.OK, new CommonResponse<>(service.getStudentById(request)));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }

    public ResponseEntity<CommonResponse<List<StudentResponse>>> getStudentsByGroup(IdRequest request) {
        try {
            return new ResponseEntity<>(HttpStatus.OK, new CommonResponse<>(service.getStudentsByGroup(request)));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }
}
