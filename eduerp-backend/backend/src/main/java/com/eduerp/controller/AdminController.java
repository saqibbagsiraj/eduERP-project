package com.eduerp.controller;

import com.eduerp.dto.request.StudentRequest;
import com.eduerp.dto.request.FacultyRequest;
import com.eduerp.dto.request.CourseRequest;

import com.eduerp.dto.response.AdminStatsResponse;
import com.eduerp.dto.response.RecentUserResponse;
import com.eduerp.dto.response.RevenueChartResponse;
import com.eduerp.dto.response.SearchResponse;
import com.eduerp.dto.response.StudentGrowthResponse;
import com.eduerp.repository.CourseRepository;
import com.eduerp.entity.Course;
import com.eduerp.entity.Faculty;
import com.eduerp.entity.Student;
import com.eduerp.service.AdminService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/stats")
    public AdminStatsResponse getDashboardStats() {
        return adminService.getDashboardStats();
    }

    @GetMapping("/recent-users")
public List<RecentUserResponse> getRecentUsers() {
    return adminService.getRecentUsers();
}

@GetMapping("/student-growth")
public List<StudentGrowthResponse> getStudentGrowth() {
    return adminService.getStudentGrowth();
}

    @GetMapping("/revenue-chart")
public List<RevenueChartResponse> getRevenueChart() {
    return adminService.getRevenueChart();
}

@GetMapping("/search")
public List<SearchResponse> search(@RequestParam String q) {
    return adminService.globalSearch(q);
}

@PostMapping("/students")
public Student addStudent(@RequestBody StudentRequest request) {
    return adminService.addStudent(request);
}

@PostMapping("/faculty")
public Faculty addFaculty(@RequestBody FacultyRequest request) {
    return adminService.addFaculty(request);
}

@PostMapping("/courses")
public Course addCourse(@RequestBody CourseRequest request) {
    return adminService.addCourse(request);
}
}