package com.eduerp.service.impl;

import com.eduerp.entity.Fee;
import com.eduerp.repository.FeeRepository;
import com.eduerp.service.FeeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FeeServiceImpl implements FeeService {

    private final FeeRepository feeRepository;

    public FeeServiceImpl(FeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }

    @Override
    public Fee createFee(Fee fee) {
        if ("PAID".equalsIgnoreCase(fee.getStatus())) {
            fee.setPaymentDate(LocalDate.now());
        }
        return feeRepository.save(fee);
    }

    @Override
    public List<Fee> getAllFees() {
        return feeRepository.findAll();
    }

    @Override
    public Fee getFeeById(Long id) {
        return feeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fee record not found"));
    }

    @Override
    public List<Fee> getFeesByStudent(Long studentId) {
        return feeRepository.findByStudentId(studentId);
    }
}