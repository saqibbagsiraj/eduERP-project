package com.eduerp.repository;

import com.eduerp.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    Optional<Enrollment> findByStudent_IdAndCourse_Id(Long studentId, Long courseId);

}