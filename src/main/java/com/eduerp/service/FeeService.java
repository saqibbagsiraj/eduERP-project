package com.eduerp.service;

import com.eduerp.entity.Fee;

import java.util.List;

public interface FeeService {

    Fee createFee(Fee fee);

    List<Fee> getAllFees();

    Fee getFeeById(Long id);

    List<Fee> getFeesByStudent(Long studentId);
}