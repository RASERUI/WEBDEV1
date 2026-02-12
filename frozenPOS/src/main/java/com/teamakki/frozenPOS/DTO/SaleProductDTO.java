package com.teamakki.frozenPOS.dto;

public class SaleProductDTO {
    private Long saleProductId;
    private Long saleId;
    private Long productId;
    private Double basePrice;
    private Double listPrice;
    private Integer quantity;
    private Long invId;

    // Getters/Setters
    public Long getSaleProductId() { return saleProductId; }
    public void setSaleProductId(Long saleProductId) { this.saleProductId = saleProductId; }

    public Long getSaleId() { return saleId; }
    public void setSaleId(Long saleId) { this.saleId = saleId; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Double getBasePrice() { return basePrice; }
    public void setBasePrice(Double basePrice) { this.basePrice = basePrice; }

    public Double getListPrice() { return listPrice; }
    public void setListPrice(Double listPrice) { this.listPrice = listPrice; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Long getInvId() { return invId; }
    public void setInvId(Long invId) { this.invId = invId; }
}
