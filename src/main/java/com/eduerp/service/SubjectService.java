package com.eduerp.service;

import com.eduerp.entity.Subject;
import java.util.List;

public interface SubjectService {

    Subject saveSubject(Subject subject);

    List<Subject> getAllSubjects();

    List<Subject> getSubjectsByCourse(Long courseId);

    Subject getSubjectById(Long id);
}