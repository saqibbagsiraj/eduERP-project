package com.eduerp.repository;

import com.eduerp.entity.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface FeeRepository extends JpaRepository<Fee, Long> {

    @Query("""
        SELECT SUM(f.amount)
        FROM Fee f
        WHERE f.status = 'PAID'
    """)
    BigDecimal getTotalRevenue();

    @Query("""
        SELECT FUNCTION('TO_CHAR', f.paymentDate, 'Mon'),
               SUM(f.amount)
        FROM Fee f
        WHERE f.status = 'PAID'
        GROUP BY FUNCTION('TO_CHAR', f.paymentDate, 'Mon')
        ORDER BY MIN(f.paymentDate)
    """)
    List<Object[]> getRevenueChartRaw();
}