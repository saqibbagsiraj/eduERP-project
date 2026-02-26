package com.eduerp.service;

import com.eduerp.entity.Student;
import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    Student getStudentById(Long id);
}