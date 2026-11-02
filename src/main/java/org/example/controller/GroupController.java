package org.example.controller;

import org.example.exception.ServerException;
import org.example.exception.ValidationsException;
import org.example.request.IdRequest;
import org.example.request.group_request.AddGroupRequest;
import org.example.request.group_request.EditGroupRequest;
import org.example.response.*;
import org.example.service.GroupService;

import java.util.List;

public class GroupController {
    private final GroupService service;

    public GroupController(GroupService service) {
        this.service = service;
    }

    public ResponseEntity<CommonResponse<IdResponse>> addStudentGroup(AddGroupRequest request) {
        try {
            return new ResponseEntity<>(HttpStatus.CREATED, new CommonResponse<>(service.addStudentGroup(request)));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }

    public ResponseEntity<CommonResponse<Void>> editStudentGroup(EditGroupRequest request) {
        try {
            return new ResponseEntity<>(HttpStatus.OK, new CommonResponse<>(service.editStudentGroup(request)));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }

    public ResponseEntity<CommonResponse<Void>> deleteStudentGroup(IdRequest request) {
        try {
            return new ResponseEntity<>(HttpStatus.OK, new CommonResponse<>(service.deleteStudentGroup(request)));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }

    public ResponseEntity<CommonResponse<List<GroupResponse>>> getStudentGroups() {
        try {
            return new ResponseEntity<>(HttpStatus.OK, new CommonResponse<>(service.getStudentGroups()));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }

    public ResponseEntity<CommonResponse<GroupResponse>> getStudentGroupById(IdRequest request) {
        try {
            return new ResponseEntity<>(HttpStatus.OK, new CommonResponse<>(service.getStudentGroupById(request)));
        } catch (ValidationsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (ServerException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY, new CommonResponse<>(e.getCode(), e.getErrors()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVER_ERROR, new CommonResponse<>(500, null));
        }
    }

}
