package com.eduerp.service.impl;

import com.eduerp.entity.Faculty;
import com.eduerp.repository.FacultyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FacultyServiceImplTest {

    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private FacultyServiceImpl facultyService;

    @Test
    void saveFacultyReturnsSavedFaculty() {
        Faculty faculty = Faculty.builder().id(1L).phone("9876543210").build();
        when(facultyRepository.save(faculty)).thenReturn(faculty);

        Faculty result = facultyService.saveFaculty(faculty);

        assertEquals(faculty, result);
        verify(facultyRepository).save(faculty);
        System.out.println("Faculty saved successfully");
    }

    @Test
    void getAllFacultyReturnsRepositoryResult() {
        List<Faculty> facultyList = List.of(
                Faculty.builder().id(1L).phone("1111111111").build(),
                Faculty.builder().id(2L).phone("2222222222").build()
        );
        when(facultyRepository.findAll()).thenReturn(facultyList);

        List<Faculty> result = facultyService.getAllFaculty();

        assertEquals(facultyList, result);
        verify(facultyRepository).findAll();
        System.out.println("Faculty list retrieved successfully");
    }
}
