package com.eduerp.service;

import com.eduerp.dto.request.EnrollmentRequest;
import com.eduerp.entity.Enrollment;

public interface EnrollmentService {

    Enrollment enrollStudent(EnrollmentRequest request);

}