package com.eduerp.repository;

import com.eduerp.dto.response.SearchResponse;
import com.eduerp.entity.Course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("""
        SELECT new com.eduerp.dto.response.SearchResponse(
            c.id,
            c.name,
            'course'
        )
        FROM Course c
        WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :q, '%'))
    """)
    List<SearchResponse> searchCourses(@Param("q") String q);

}