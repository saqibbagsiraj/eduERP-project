package com.eduerp.service;

import com.eduerp.entity.Course;

import java.util.List;

public interface CourseService {

    Course saveCourse(Course course);

    List<Course> getAllCourses();

    Course getCourseById(Long id);
}