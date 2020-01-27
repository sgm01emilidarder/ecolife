package com.ecolife.dto;

import java.time.LocalDate;
import java.util.Objects;

public class Order {
    private int id;
    private User userDni;
    private LocalDate orderDate;
    private double orderTotal;

    public Order(int id) {
        this.id = id;
    }

    public Order(int id, User userDni, LocalDate orderDate, double orderTotal) {
        this.id = id;
        this.userDni = userDni;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserDni() {
        return userDni;
    }

    public void setUserDni(User userDni) {
        this.userDni = userDni;
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
                userDni.equals(order.userDni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userDni);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userDni=" + userDni +
                ", orderDate=" + orderDate +
                ", orderTotal=" + orderTotal +
                '}';
    }
}
