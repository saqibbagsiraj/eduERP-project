package com.eduerp.service.impl;

import com.eduerp.entity.Exam;
import com.eduerp.repository.ExamRepository;
import com.eduerp.service.ExamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;

    public ExamServiceImpl(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @Override
    public Exam getExamById(Long id) {
        return examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
    }

    @Override
    public List<Exam> getExamsBySubject(Long subjectId) {
        return examRepository.findBySubjectId(subjectId);
    }
}