package com.teamakki.frozenPOS.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
public class Inventory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invId;

    private Integer quantity;
    private LocalDateTime lastUpdated;
    private String unit;

    // Getters / Setters
    public Long getInvId() { return invId; }
    public void setInvId(Long invId) { this.invId = invId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
}
