package com.eduerp.repository;

import com.eduerp.dto.response.SearchResponse;
import com.eduerp.dto.response.StudentGrowthResponse;
import com.eduerp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = """
        SELECT 
            TO_CHAR(u.created_at, 'Mon') AS month,
            COUNT(s.student_id) AS count
        FROM students s
        JOIN users u ON s.user_id = u.user_id
        GROUP BY month
        ORDER BY MIN(u.created_at)
    """, nativeQuery = true)
    List<Object[]> getStudentGrowthRaw();

    @Query("""
SELECT new com.eduerp.dto.response.SearchResponse(
s.id,
s.user.name,
'student'
)
FROM Student s
WHERE LOWER(s.user.name) LIKE LOWER(CONCAT('%', :q, '%'))
""")
List<SearchResponse> searchStudents(String q);

}