package com.ecolife.dto;

import java.time.LocalDate;
import java.util.Objects;

public class Order {
    private int id;
    private User userId;
    private LocalDate orderDate;
    private double orderTotal;

    public Order(int id) {
        this.id = id;
    }

    public Order(int id, User userId, LocalDate orderDate, double orderTotal) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setuserId(User userId) {
        this.userId = userId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id &&
                userId.equals(order.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderDate=" + orderDate +
                ", orderTotal=" + orderTotal +
                '}';
    }
}
