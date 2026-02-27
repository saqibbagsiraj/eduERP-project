package com.eduerp.service;

import com.eduerp.entity.Course;
import com.eduerp.entity.Enrollment;

import java.util.List;

public interface CourseService {

    Course saveCourse(Course course);

    List<Course> getAllCourses();

    Course getCourseById(Long id);

    interface EnrollmentService {

        Enrollment enrollStudent(Enrollment enrollment);

        List<Enrollment> getAllEnrollments();

        List<Enrollment> getEnrollmentsByStudent(Long studentId);

        List<Enrollment> getEnrollmentsByCourse(Long courseId);
    }
}