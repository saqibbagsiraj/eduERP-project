package com.eduerp.service.impl;

import com.eduerp.entity.Fee;
import com.eduerp.repository.FeeRepository;
import com.eduerp.service.FeeService;
import org.springframework.stereotype.Service;

@Service
public class FeeServiceImpl implements FeeService {

    private final FeeRepository feeRepository;

    public FeeServiceImpl(FeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }

    @Override
    public Fee addFee(Fee fee) {
        return feeRepository.save(fee);
    }
}