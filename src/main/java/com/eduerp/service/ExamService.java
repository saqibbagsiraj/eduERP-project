package com.eduerp.service;

import com.eduerp.entity.Exam;

import java.util.List;

public interface ExamService {

    Exam createExam(Exam exam);

    List<Exam> getAllExams();

    Exam getExamById(Long id);

    List<Exam> getExamsBySubject(Long subjectId);
}