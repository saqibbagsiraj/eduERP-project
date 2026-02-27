package com.eduerp.service.impl;

import com.eduerp.entity.Course;
import com.eduerp.repository.CourseRepository;
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
class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void getAllCoursesReturnsRepositoryResult() {
        List<Course> courses = List.of(
                Course.builder().id(1L).name("Math").build(),
                Course.builder().id(2L).name("Physics").build()
        );
        when(courseRepository.findAll()).thenReturn(courses);

        List<Course> result = courseService.getAllCourses();

        assertEquals(courses, result);
        verify(courseRepository).findAll();
        System.out.println("Courses retrieved successfully");
    }

    @Test
    void getCourseByIdReturnsCourseWhenFound() {
        Course course = Course.builder().id(5L).name("Chemistry").build();
        when(courseRepository.findById(5L)).thenReturn(Optional.of(course));

        Course result = courseService.getCourseById(5L);

        assertEquals(course, result);
        verify(courseRepository).findById(5L);
        System.out.println("Course found successfully");
    }

    @Test
    void getCourseByIdThrowsWhenNotFound() {
        when(courseRepository.findById(77L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> courseService.getCourseById(77L));

        assertEquals("Course not found", exception.getMessage());
        verify(courseRepository).findById(77L);

    }
}
