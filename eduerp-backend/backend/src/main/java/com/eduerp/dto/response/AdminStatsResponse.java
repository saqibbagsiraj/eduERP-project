package com.eduerp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AdminStatsResponse {

    private Long totalStudents;
    private Long totalFaculty;
    private Long totalCourses;
    private BigDecimal totalRevenue;

}