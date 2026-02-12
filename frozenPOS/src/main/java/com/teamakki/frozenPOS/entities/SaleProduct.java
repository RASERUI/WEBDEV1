package com.teamakki.frozenPOS.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sale_product")
public class SaleProduct {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleProductId;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "inv_id")
    private Inventory inventory;

    private Double basePrice;
    private Double listPrice;
    private Integer quantity;

    // Getters / Setters
    public Long getSaleProductId() { return saleProductId; }
    public void setSaleProductId(Long saleProductId) { this.saleProductId = saleProductId; }

    public Sale getSale() { return sale; }
    public void setSale(Sale sale) { this.sale = sale; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public Inventory getInventory() { return inventory; }
    public void setInventory(Inventory inventory) { this.inventory = inventory; }

    public Double getBasePrice() { return basePrice; }
    public void setBasePrice(Double basePrice) { this.basePrice = basePrice; }

    public Double getListPrice() { return listPrice; }
    public void setListPrice(Double listPrice) { this.listPrice = listPrice; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
