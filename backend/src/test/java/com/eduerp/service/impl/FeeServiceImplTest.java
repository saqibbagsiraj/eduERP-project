package com.eduerp.service.impl;

import com.eduerp.entity.Fee;
import com.eduerp.repository.FeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeeServiceImplTest {

    @Mock
    private FeeRepository feeRepository;

    @InjectMocks
    private FeeServiceImpl feeService;

    @Test
    void addFeeReturnsSavedFee() {
        Fee fee = Fee.builder().id(1L).amount(new BigDecimal("1200.00")).status("PAID").build();
        when(feeRepository.save(fee)).thenReturn(fee);

        Fee result = feeService.addFee(fee);

        assertEquals(fee, result);
        verify(feeRepository).save(fee);
        System.out.println("Fee added successfully");
    }
}
