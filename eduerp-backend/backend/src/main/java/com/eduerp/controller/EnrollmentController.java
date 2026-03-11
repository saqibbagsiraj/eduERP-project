package com.eduerp.controller;

import com.eduerp.dto.request.EnrollmentRequest;
import com.eduerp.entity.Enrollment;
import com.eduerp.service.EnrollmentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public Enrollment enrollStudent(@Valid @RequestBody EnrollmentRequest request) {

        return enrollmentService.enrollStudent(request);
    }
}