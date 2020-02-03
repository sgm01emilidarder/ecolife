package com.ecolife.dto;

import java.util.*;

public class OrderItems {
    private Order orderId;
    private Product productCode;
    private double unitPrice;
    private double quantity;

    public OrderItems() {};

    public OrderItems(Order orderId, Product productCode) {
        this.orderId = orderId;
        this.productCode = productCode;
    }

    public OrderItems(Product productCode, double unitPrice, double quantity) {
        this.productCode = productCode;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public OrderItems(Order orderId, Product productCode, double unitPrice, double quantity) {
        this.orderId = orderId;
        this.productCode = productCode;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public Product getProductCode() {
        return productCode;
    }

    public void setProductCode(Product productCode) {
        this.productCode = productCode;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItems)) return false;
        OrderItems that = (OrderItems) o;
        return orderId.equals(that.orderId) &&
                productCode.equals(that.productCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productCode);
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "orderId=" + orderId +
                ", productCode=" + productCode +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                '}';
    }
}
