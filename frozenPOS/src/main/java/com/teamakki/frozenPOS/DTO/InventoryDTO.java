package com.teamakki.frozenPOS.dto;

import java.time.LocalDateTime;

public class InventoryDTO {
    private Long invId;
    private Integer quantity;
    private LocalDateTime lastUpdated;
    private String unit;

    public Long getInvId() { return invId; }
    public void setInvId(Long invId) { this.invId = invId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
}
