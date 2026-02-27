package com.eduerp.controller;

import com.eduerp.entity.Fee;
import com.eduerp.service.FeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fees")
public class FeeController {

    private final FeeService feeService;

    public FeeController(FeeService feeService) {
        this.feeService = feeService;
    }

    @PostMapping
    public Fee createFee(@RequestBody Fee fee) {
        return feeService.createFee(fee);
    }

    @GetMapping
    public List<Fee> getAllFees() {
        return feeService.getAllFees();
    }

    @GetMapping("/{id}")
    public Fee getFeeById(@PathVariable Long id) {
        return feeService.getFeeById(id);
    }

    @GetMapping("/student/{studentId}")
    public List<Fee> getFeesByStudent(@PathVariable Long studentId) {
        return feeService.getFeesByStudent(studentId);
    }
}