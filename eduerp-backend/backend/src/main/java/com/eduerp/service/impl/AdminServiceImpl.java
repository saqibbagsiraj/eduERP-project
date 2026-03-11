package com.eduerp.service.impl;

import com.eduerp.dto.request.CourseRequest;
import com.eduerp.dto.request.StudentRequest;
import com.eduerp.dto.request.FacultyRequest;

import com.eduerp.dto.response.AdminStatsResponse;
import com.eduerp.dto.response.RecentUserResponse;
import com.eduerp.dto.response.StudentGrowthResponse;
import com.eduerp.entity.Course;
import com.eduerp.entity.Faculty;
import com.eduerp.entity.Student;
import com.eduerp.entity.User;
import com.eduerp.dto.response.RevenueChartResponse;
import com.eduerp.dto.response.SearchResponse;
import com.eduerp.repository.CourseRepository;
import com.eduerp.repository.FacultyRepository;
import com.eduerp.repository.FeeRepository;
import com.eduerp.repository.StudentRepository;
import com.eduerp.repository.UserRepository;

import com.eduerp.service.AdminService;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;



@Service
public class AdminServiceImpl implements AdminService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final CourseRepository courseRepository;
    private final FeeRepository feeRepository;
    private final UserRepository userRepository;

    public AdminServiceImpl(
            StudentRepository studentRepository,
            FacultyRepository facultyRepository,
            CourseRepository courseRepository,
            FeeRepository feeRepository,
            UserRepository userRepository) {

        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.courseRepository = courseRepository;
        this.feeRepository = feeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AdminStatsResponse getDashboardStats() {

        Long students = studentRepository.count();
        Long faculty = facultyRepository.count();
        Long courses = courseRepository.count();

        BigDecimal revenue = feeRepository.getTotalRevenue();

        return new AdminStatsResponse(
                students,
                faculty,
                courses,
                revenue == null ? BigDecimal.ZERO : revenue
        );
    }

    @Override
    public List<RecentUserResponse> getRecentUsers() {
        return userRepository.findRecentUsers(PageRequest.of(0,5));
    }

    @Override
public List<StudentGrowthResponse> getStudentGrowth() {

    return studentRepository.getStudentGrowthRaw()
            .stream()
            .map(row -> new StudentGrowthResponse(
                    (String) row[0],
                    ((Number) row[1]).longValue()
            ))
            .toList();
}

   @Override
public List<RevenueChartResponse> getRevenueChart() {

    return feeRepository.getRevenueChartRaw()
            .stream()
            .map(row -> new RevenueChartResponse(
                    (String) row[0],
                    (java.math.BigDecimal) row[1]
            ))
            .toList();
}

@Override
public List<SearchResponse> globalSearch(String q) {

    List<SearchResponse> results = new ArrayList<>();

    results.addAll(studentRepository.searchStudents(q));
    results.addAll(facultyRepository.searchFaculty(q));
    results.addAll(courseRepository.searchCourses(q));

    return results;
}

@Override
public Student addStudent(StudentRequest request) {

    User user = new User();
    user.setName(request.getName());
    user.setEmail(request.getEmail());

    userRepository.save(user);

    Student student = new Student();
    student.setUser(user);

    return studentRepository.save(student);
}


@Override
public Faculty addFaculty(FacultyRequest request) {

    User user = new User();
    user.setName(request.getName());
    user.setEmail(request.getEmail());

    userRepository.save(user);

    Faculty faculty = new Faculty();
    faculty.setUser(user);

    return facultyRepository.save(faculty);
}

@Override
public Course addCourse(CourseRequest request) {

    Course course = new Course();

    course.setName(request.getName());
    course.setCode(request.getCode());
    course.setDescription(request.getDescription());
    course.setCredits(request.getCredits());

    return courseRepository.save(course);
}
}