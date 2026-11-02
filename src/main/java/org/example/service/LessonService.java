package org.example.service;

import org.example.converter.attendance_converter.AttendanceEntityConverter;
import org.example.converter.lesson_converter.LessonEntityConverter;
import org.example.converter.lesson_converter.LessonRequestConverter;
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
import org.example.request.lesson_request.AddLessonRequest;
import org.example.request.lesson_request.EditLessonRequest;
import org.example.request.lesson_request.GetLessonsByGroupRequest;
import org.example.request.lesson_request.GetLessonsByTeacherRequest;
import org.example.response.*;
import org.example.validators.request.IdRequestValidator;
import org.example.validators.request.lesson_validator.AddLessonValidator;
import org.example.validators.request.lesson_validator.EditLessonValidator;
import org.example.validators.request.lesson_validator.GetLessonsByGroupValidator;
import org.example.validators.request.lesson_validator.GetLessonsByTeacherValidator;

import java.util.List;

public class LessonService {
    private final ILessonRepository lessonRepository;
    private final IGroupRepository groupRepository;
    private final ISubjectRepository subjectRepository;
    private final ITeacherRepository teacherRepository;
    private final IAttendanceRepository attendanceRepository;
    private final IStudentRepository studentRepository;
    private final AddLessonValidator addLessonValidator;
    private final EditLessonValidator editLessonValidator;
    private final LessonEntityConverter lessonEntityConverter;
    private final LessonRequestConverter lessonRequestConverter;
    private final GetLessonsByGroupValidator getLessonsByGroupValidator;
    private final GetLessonsByTeacherValidator getLessonsByTeacherValidator;
    private final AttendanceEntityConverter attendanceEntityConverter;
    private final IdRequestValidator idRequestValidator;

    public LessonService(ILessonRepository lessonRepository, IGroupRepository groupRepository, ISubjectRepository subjectRepository, ITeacherRepository teacherRepository, IAttendanceRepository attendanceRepository, IStudentRepository studentRepository, AddLessonValidator addLessonValidator, EditLessonValidator editLessonValidator, LessonEntityConverter lessonEntityConverter, LessonRequestConverter lessonRequestConverter, GetLessonsByGroupValidator getLessonsByGroupValidator, GetLessonsByTeacherValidator getLessonsByTeacherValidator, AttendanceEntityConverter attendanceEntityConverter, IdRequestValidator idRequestValidator) {
        this.idRequestValidator = idRequestValidator;
        this.lessonRepository = lessonRepository;
        this.groupRepository = groupRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.addLessonValidator = addLessonValidator;
        this.editLessonValidator = editLessonValidator;
        this.lessonEntityConverter = lessonEntityConverter;
        this.lessonRequestConverter = lessonRequestConverter;
        this.getLessonsByGroupValidator = getLessonsByGroupValidator;
        this.getLessonsByTeacherValidator = getLessonsByTeacherValidator;
        this.attendanceEntityConverter = attendanceEntityConverter;
    }

    public IdResponse addLesson(AddLessonRequest request) throws ServerException {
        List<String> errors = addLessonValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on adding lesson", ServerErrors.LESSON_VALIDATION_ERROR.getCode(), errors);
        }
        LessonEntity lessonEntity = lessonRequestConverter.toEntity(request);
        try {
            long id = lessonRepository.create(lessonEntity);
            return new IdResponse(id);
        } catch (Exception e) {
            throw new ServerException("Unable to add lesson: DB error", ServerErrors.LESSON_ADD_ERROR.getCode(), null);
        }
    }

    public Void editLesson(EditLessonRequest request) throws ServerException {
        List<String> errors = editLessonValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on editing lesson", ServerErrors.LESSON_VALIDATION_ERROR.getCode(), errors);
        }
        try {
            LessonEntity existingLesson = lessonRepository.getById(request.getId());
            LessonEntity lessonEntity = lessonRequestConverter.toEntity(request, existingLesson.getCreatedAt());
            lessonRepository.update(lessonEntity);
        } catch (Exception e) {
            throw new ServerException("Unable to edit lesson: DB error", ServerErrors.LESSON_EDIT_ERROR.getCode(), null);
        }
        return null;
    }

    public Void deleteLesson(IdRequest request) throws ServerException {
        List<String> errors = idRequestValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on deleting lesson", ServerErrors.LESSON_VALIDATION_ERROR.getCode(), errors);
        }
        try {
            lessonRepository.deleteById(request.getId());
        } catch (Exception e) {
            throw new ServerException("Unable to delete lesson: DB error", ServerErrors.LESSON_DELETE_ERROR.getCode(), null);
        }
        return null;
    }

    public List<LessonResponse> getLessonsByGroup(GetLessonsByGroupRequest request) throws ServerException {
        List<String> errors = getLessonsByGroupValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on getting lesson", ServerErrors.LESSON_VALIDATION_ERROR.getCode(), errors);
        }
        try {
            return lessonRepository.getLessonsByGroup(request.getStartDate(), request.getEndDate(), request.getGroupId()).stream()
                    .map(this::toResponse)
                    .toList();
        } catch (Exception e) {
            throw new ServerException("Unable to get lesson: DB error", ServerErrors.LESSON_GET_ERROR.getCode(), null);
        }
    }

    public List<LessonResponse> getLessonsByTeacher(GetLessonsByTeacherRequest request) throws ServerException {
        List<String> errors = getLessonsByTeacherValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on getting lesson", ServerErrors.LESSON_VALIDATION_ERROR.getCode(), errors);
        }
        try {
            return lessonRepository.getLessonsByTeacher(request.getStartDate(), request.getEndDate(), request.getTeacherId()).stream()
                    .map(this::toResponse)
                    .toList();
        } catch (Exception e) {
            throw new ServerException("Unable to get lesson: DB error", ServerErrors.LESSON_GET_ERROR.getCode(), null);
        }
    }

    public LessonWithAttendanceResponse getLessonById(IdRequest request) throws ServerException {
        List<String> errors = idRequestValidator.validate(request);
        if (!errors.isEmpty()) {
            throw new ServerException("Failed validation on getting lesson", ServerErrors.LESSON_VALIDATION_ERROR.getCode(), errors);
        }
        try {
            LessonEntity lesson = lessonRepository.getById(request.getId());
            SubjectEntity subject = subjectRepository.getById(lesson.getSubjectId());
            TeacherEntity teacher = teacherRepository.getById(lesson.getTeacherId());
            List<GroupEntity> groups = lesson.getGroupsId().stream()
                    .map(groupRepository::getById)
                    .toList();
            List<AttendanceResponse> attendance = attendanceRepository.getByLessonId(lesson.getId()).stream()
                    .map(this::toAttendanceResponse)
                    .toList();
            return lessonEntityConverter.toLessonWithAttendanceResponse(lesson, subject, teacher, groups, attendance);
        } catch (Exception e) {
            throw new ServerException("Unable to get lesson: DB error", ServerErrors.LESSON_GET_ERROR.getCode(), null);
        }
    }

    private LessonResponse toResponse(LessonEntity lesson) {
        SubjectEntity subject = subjectRepository.getById(lesson.getSubjectId());
        TeacherEntity teacher = teacherRepository.getById(lesson.getTeacherId());
        List<GroupEntity> groups = lesson.getGroupsId().stream()
                .map(groupRepository::getById)
                .toList();
        return lessonEntityConverter.toResponse(lesson, subject, teacher, groups);
    }

    private AttendanceResponse toAttendanceResponse(AttendanceEntity attendance) {
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
