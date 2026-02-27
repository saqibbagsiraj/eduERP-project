package com.eduerp.service.impl;

import com.eduerp.entity.Attendance;
import com.eduerp.repository.AttendanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AttendanceServiceImplTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @InjectMocks
    private AttendanceServiceImpl attendanceService;

    @Test
    void markAttendanceReturnsSavedAttendance() {
        Attendance attendance = Attendance.builder()
                .id(1L)
                .attendanceDate(LocalDate.of(2026, 2, 27))
                .status("PRESENT")
                .build();
        when(attendanceRepository.save(attendance)).thenReturn(attendance);

        Attendance result = attendanceService.markAttendance(attendance);

        assertEquals(attendance, result);
        verify(attendanceRepository).save(attendance);
        System.out.println("Attendance marked successfully");
    }
}
