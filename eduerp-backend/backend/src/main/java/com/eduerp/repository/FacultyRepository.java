package com.eduerp.repository;

import com.eduerp.dto.response.SearchResponse;
import com.eduerp.entity.Faculty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    @Query("""
SELECT new com.eduerp.dto.response.SearchResponse(
f.id,
f.user.name,
'faculty'
)
FROM Faculty f
WHERE LOWER(f.user.name) LIKE LOWER(CONCAT('%', :q, '%'))
""")
List<SearchResponse> searchFaculty(String q);
}