package com.eduerp.service.impl;

import com.eduerp.dto.request.EnrollmentRequest;
import com.eduerp.entity.Enrollment;
import com.eduerp.entity.Student;
import com.eduerp.entity.Course;
import com.eduerp.repository.EnrollmentRepository;
import com.eduerp.repository.StudentRepository;
import com.eduerp.repository.CourseRepository;
import com.eduerp.service.EnrollmentService;

import org.springframework.stereotype.Service;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentServiceImpl(
            EnrollmentRepository enrollmentRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository) {

        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Enrollment enrollStudent(EnrollmentRequest request) {

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        enrollmentRepository.findByStudent_IdAndCourse_Id(
                request.getStudentId(),
                request.getCourseId()
        ).ifPresent(e -> {
            throw new RuntimeException("Student already enrolled in this course");
        });

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .build();

        return enrollmentRepository.save(enrollment);
    }
}