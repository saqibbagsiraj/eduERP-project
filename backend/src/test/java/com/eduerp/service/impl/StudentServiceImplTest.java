package com.eduerp.service.impl;

import com.eduerp.entity.Student;
import com.eduerp.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void saveStudentReturnsSavedEntity() {
        Student student = Student.builder().id(1L).rollNo("R001").phone("12345").build();
        when(studentRepository.save(student)).thenReturn(student);

        Student savedStudent = studentService.saveStudent(student);

        assertEquals(student, savedStudent);
        verify(studentRepository).save(student);
    }

    @Test
    void getAllStudentsReturnsRepositoryResult() {
        List<Student> students = List.of(
                Student.builder().id(1L).rollNo("R001").build(),
                Student.builder().id(2L).rollNo("R002").build()
        );
        when(studentRepository.findAll()).thenReturn(students);

        List<Student> result = studentService.getAllStudents();

        assertEquals(students, result);
        verify(studentRepository).findAll();
    }

    @Test
    void getStudentByIdReturnsStudentWhenFound() {
        Student student = Student.builder().id(10L).rollNo("R010").build();
        when(studentRepository.findById(10L)).thenReturn(Optional.of(student));

        Student result = studentService.getStudentById(10L);

        assertEquals(student, result);
        verify(studentRepository).findById(10L);
    }

    @Test
    void getStudentByIdThrowsWhenNotFound() {
        when(studentRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> studentService.getStudentById(99L));

        assertEquals("Student not found", exception.getMessage());
        verify(studentRepository).findById(99L);
    }
}
