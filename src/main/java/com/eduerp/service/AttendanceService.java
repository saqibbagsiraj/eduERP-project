package com.eduerp.service;

import com.eduerp.entity.Attendance;
import java.util.List;

public interface AttendanceService {

    Attendance markAttendance(Attendance attendance);

    List<Attendance> getAllAttendance();

    List<Attendance> getAttendanceByStudent(Long studentId);

    List<Attendance> getAttendanceBySubject(Long subjectId);
}