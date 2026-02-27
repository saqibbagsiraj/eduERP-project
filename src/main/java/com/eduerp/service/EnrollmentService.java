package com.eduerp.service;

import com.eduerp.entity.Enrollment;
import java.util.List;

public interface EnrollmentService {

    Enrollment enrollStudent(Enrollment enrollment);

    List<Enrollment> getAllEnrollments();

    List<Enrollment> getEnrollmentsByStudent(Long studentId);

    List<Enrollment> getEnrollmentsByCourse(Long courseId);
}