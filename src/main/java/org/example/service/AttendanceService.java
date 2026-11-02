package org.example.service;

import org.example.converter.attendance_converter.AttendanceEntityConverter;
import org.example.converter.attendance_converter.AttendanceRequestConverter;
import org.example.entity.*;
import org.example.exception.ServerErrors;
import org.example.exception.ServerException;
import org.example.repository.attendace_repository.IAttendanceRepository;
import org.example.repository.group_repository.IGroupRepository;
import org.example.repository.lesson_repository.ILessonRepository;
import org.example.repository.student_repository.IStudentRepository;
import org.example.repository.subject_repository.ISubjectRepository;
import org.example.repository.teacher_repository.ITeacherRepository;
import org.example.request.IdRequest;
import org.example.request.attendance_request.AddAttendanceRequest;
import org.example.request.attendance_request.EditAttendanceRequest;
import org.example.response.*;
import org.example.validators.request.IdRequestValidator;
import org.example.validators.request.attendance_validator.AddAttendanceValidator;
import org.example.validators.request.attendance_validator.EditAttendanceValidator;

import java.util.List;

public class AttendanceService {
    private final IAttendanceRepository attendanceRepository;
    private final ILessonRepository lessonRepository;
    private final IStudentRepository studentRepository;
    private final IGroupRepository groupRepository;
    private final ISubjectRepository subjectRepository;
    private final ITeacherRepository teacherRepository;
    private final AddAttendanceValidator addAttendanceValidator;
    private final EditAttendanceValidator editAttendanceValidator;
    private final AttendanceEntityConverter attendanceEntityConverter;
    private final AttendanceRequestConverter attendanceRequestConverter;
    private final IdRequestValidator idRequestValidator;

    public AttendanceService(IAttendanceRepository attendanceRepository, ILessonRepository lessonRepository, IStudentRepository studentRepository, IGroupRepository groupRepository, ISubjectRepository subjectRepository, ITeacherRepository teacherRepository, AddAttendanceValidator addAttendanceValidator, EditAttendanceValidator editAttendanceValidator, AttendanceEntityConverter attendanceEntityConverter, AttendanceRequestConverter attendanceRequestConverter, IdRequestValidator idRequestValidator) {
        this.idRequestValidator = idRequestValidator;
        this.attendanceRepository = attendanceRepository;
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.addAttendanceValidator = addAttendanceValidator;
        this.editAttendanceValidator = editAttendanceValidator;
        this.attendanceEntityConverter = attendanceEntityConverter;
        this.attendanceRequestConverter = attendanceRequestConverter;
    }

    public IdResponse addAttendance(AddAttendanceRequest request) throws ServerException {
        List<String> errors = addAttendanceValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on adding attendance", ServerErrors.ATTENDANCE_VALIDATION_ERROR.getCode(), errors);
        }
        AttendanceEntity attendanceEntity = attendanceRequestConverter.toEntity(request);
        try {
            long id = attendanceRepository.create(attendanceEntity);
            return new IdResponse(id);
        } catch (Exception e) {
            throw new ServerException("Unable to add attendance: DB error", ServerErrors.ATTENDANCE_ADD_ERROR.getCode(), null);
        }
    }

    public Void editAttendance(EditAttendanceRequest request) throws ServerException {
        List<String> errors = editAttendanceValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on editing attendance", ServerErrors.ATTENDANCE_VALIDATION_ERROR.getCode(), errors);
        }
        try {
            AttendanceEntity existingAttendance = attendanceRepository.getById(request.getId());
            AttendanceEntity attendanceEntity = attendanceRequestConverter.toEntity(request, existingAttendance.getCreatedAt());
            attendanceRepository.update(attendanceEntity);
        } catch (Exception e) {
            throw new ServerException("Unable to edit attendance: DB error", ServerErrors.ATTENDANCE_EDIT_ERROR.getCode(), null);
        }
        return null;
    }

    public Void deleteAttendance(IdRequest request) throws ServerException {
        List<String> errors = idRequestValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on deleting attendance", ServerErrors.ATTENDANCE_VALIDATION_ERROR.getCode(), errors);
        }
        try {
            attendanceRepository.deleteById(request.getId());
        } catch (Exception e) {
            throw new ServerException("Unable to delete attendance: DB error", ServerErrors.ATTENDANCE_DELETE_ERROR.getCode(), null);
        }
        return null;
    }

    public AttendanceResponse getAttendanceById(IdRequest request) throws ServerException {
        List<String> errors = idRequestValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on getting attendance", ServerErrors.ATTENDANCE_VALIDATION_ERROR.getCode(), errors);
        }
        try {
            AttendanceEntity attendanceEntity = attendanceRepository.getById(request.getId());
            return toResponse(attendanceEntity);
        } catch (Exception e) {
            throw new ServerException("Unable to get attendance: DB error", ServerErrors.ATTENDANCE_GET_ERROR.getCode(), null);
        }
    }

    public List<AttendanceResponse> getAttendanceByLesson(IdRequest request) throws ServerException {
        List<String> errors = idRequestValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on getting attendance", ServerErrors.ATTENDANCE_VALIDATION_ERROR.getCode(), errors);
        }
        try {
            return attendanceRepository.getByLessonId(request.getId()).stream()
                    .map(this::toResponse)
                    .toList();
        } catch (Exception e) {
            throw new ServerException("Unable to get attendance: DB error", ServerErrors.ATTENDANCE_GET_ERROR.getCode(), null);
        }
    }

    public List<AttendanceResponse> getAttendanceByStudent(IdRequest request) throws ServerException {
        List<String> errors = idRequestValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on getting attendance", ServerErrors.ATTENDANCE_VALIDATION_ERROR.getCode(), errors);
        }
        try {
            return attendanceRepository.getByStudentId(request.getId()).stream()
                    .map(this::toResponse)
                    .toList();
        } catch (Exception e) {
            throw new ServerException("Unable to get attendance: DB error", ServerErrors.ATTENDANCE_GET_ERROR.getCode(), null);
        }
    }

    private AttendanceResponse toResponse(AttendanceEntity attendance) {
        LessonEntity lesson = lessonRepository.getById(attendance.getLessonId());
        StudentEntity student = studentRepository.getById(attendance.getStudentId());
        GroupEntity studentGroup = groupRepository.getById(student.getGroupId());
        List<GroupEntity> lessonGroups = lesson.getGroupsId().stream()
                .map(groupRepository::getById)
                .toList();
        SubjectEntity subject = subjectRepository.getById(lesson.getSubjectId());
        TeacherEntity teacher = teacherRepository.getById(lesson.getTeacherId());
        return attendanceEntityConverter.toResponse(attendance, lesson, student, studentGroup, lessonGroups, subject, teacher);
    }
}
