package com.eduerp.dto.response;

import java.math.BigDecimal;

public class RevenueChartResponse {

    private String month;
    private BigDecimal revenue;

    public RevenueChartResponse(String month, BigDecimal revenue) {
        this.month = month;
        this.revenue = revenue;
    }

    public String getMonth() {
        return month;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }
}