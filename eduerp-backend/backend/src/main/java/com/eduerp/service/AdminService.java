package com.eduerp.service;

import java.util.List;

import com.eduerp.dto.request.StudentRequest;
import com.eduerp.dto.request.FacultyRequest;
import com.eduerp.dto.response.AdminStatsResponse;
import com.eduerp.dto.response.RecentUserResponse;
import com.eduerp.dto.response.RevenueChartResponse;
import com.eduerp.dto.response.SearchResponse;
import com.eduerp.dto.response.StudentGrowthResponse;
import com.eduerp.entity.Faculty;
import com.eduerp.entity.Student;
import com.eduerp.entity.User;
import com.eduerp.dto.request.CourseRequest;
import com.eduerp.entity.Course;

public interface AdminService {

    AdminStatsResponse getDashboardStats();

    List<RecentUserResponse> getRecentUsers();

     List<RevenueChartResponse> getRevenueChart();

    List<StudentGrowthResponse> getStudentGrowth();

    List<SearchResponse> globalSearch(String q);

    Student addStudent(StudentRequest request);

    Faculty addFaculty(FacultyRequest request);

    Course addCourse(CourseRequest request);

}