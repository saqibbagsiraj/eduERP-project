package com.eduerp.service.impl;

import com.eduerp.entity.Exam;
import com.eduerp.repository.ExamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExamServiceImplTest {

    @Mock
    private ExamRepository examRepository;

    @InjectMocks
    private ExamServiceImpl examService;

    @Test
    void createExamReturnsSavedExam() {
        Exam exam = Exam.builder().id(1L).examDate(LocalDate.of(2026, 3, 15)).maxMarks(100).build();
        when(examRepository.save(exam)).thenReturn(exam);

        Exam result = examService.createExam(exam);

        assertEquals(exam, result);
        verify(examRepository).save(exam);
        System.out.println("Exam created successfully");
    }
}
