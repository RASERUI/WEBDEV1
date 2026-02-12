package com.teamakki.frozenPOS.dto;

import java.time.LocalDateTime;
import java.util.List;

public class SaleDTO {
    private Long saleId;
    private LocalDateTime saleDateTime;
    private Double totalAmt;
    private String paymentMethod;
    private String saleType;
    private List<Long> saleProductIds;

    // Getters/Setters
    public Long getSaleId() { return saleId; }
    public void setSaleId(Long saleId) { this.saleId = saleId; }

    public LocalDateTime getSaleDateTime() { return saleDateTime; }
    public void setSaleDateTime(LocalDateTime saleDateTime) { this.saleDateTime = saleDateTime; }

    public Double getTotalAmt() { return totalAmt; }
    public void setTotalAmt(Double totalAmt) { this.totalAmt = totalAmt; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getSaleType() { return saleType; }
    public void setSaleType(String saleType) { this.saleType = saleType; }

    public List<Long> getSaleProductIds() { return saleProductIds; }
    public void setSaleProductIds(List<Long> saleProductIds) { this.saleProductIds = saleProductIds; }
}
