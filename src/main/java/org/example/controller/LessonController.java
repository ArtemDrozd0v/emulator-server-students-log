package org.example.controller;

import org.example.exception.ValidationsException;
import org.example.service.LessonService;
import org.example.exception.ServerException;
import org.example.request.IdRequest;
import org.example.request.lesson_request.AddLessonRequest;
import org.example.request.lesson_request.EditLessonRequest;
import org.example.request.lesson_request.GetLessonsByGroupRequest;
import org.example.request.lesson_request.GetLessonsByTeacherRequest;
import org.example.response.*;

import java.util.List;

public class LessonController {
    private final LessonService service;

    public LessonController(LessonService service) {
        this.service = service;
    }

    public ResponseEntity<CommonResponse<IdResponse>> addLesson(AddLessonRequest request) {
        try {
            return new ResponseEntity<>(HttpStatus.CREATED, new CommonResponse<>(service.addLesson(request)));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }

    public ResponseEntity<CommonResponse<Void>> editLesson(EditLessonRequest request) {
        try {
            return new ResponseEntity<>(HttpStatus.OK, new CommonResponse<>(service.editLesson(request)));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }

    public ResponseEntity<CommonResponse<Void>> deleteLesson(IdRequest request) {
        try {
            return new ResponseEntity<>(HttpStatus.OK, new CommonResponse<>(service.deleteLesson(request)));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }

    public ResponseEntity<CommonResponse<List<LessonResponse>>> getLessonsByGroup(GetLessonsByGroupRequest request) {
        try {
            return new ResponseEntity<>(HttpStatus.OK, new CommonResponse<>(service.getLessonsByGroup(request)));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }

    public ResponseEntity<CommonResponse<List<LessonResponse>>> getLessonsByTeacher(GetLessonsByTeacherRequest request) {
        try {
            return new ResponseEntity<>(HttpStatus.OK, new CommonResponse<>(service.getLessonsByTeacher(request)));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }

    public ResponseEntity<CommonResponse<LessonWithAttendanceResponse>> getLessonById(IdRequest request) {
        try {
            return new ResponseEntity<>(HttpStatus.OK, new CommonResponse<>(service.getLessonById(request)));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }
}
