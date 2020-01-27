package com.ecolife.dto;

import java.util.Objects;

public class Product {
    private int code;
    private String name;
    private double price;
    private double weight;
    private String cover;
    private String description;
    private Category category;
    private Type type;

    public Product(int code) {
        this.code = code;
    }

    public Product(String name, double price, double weight, String cover, String description, Category category, Type type) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.cover = cover;
        this.description = description;
        this.category = category;
        this.type = type;
    }

    public Product(int code, String name, double price, double weight, String cover, String description, Category category, Type type) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.cover = cover;
        this.description = description;
        this.category = category;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return code == product.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Product{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", cover='" + cover + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", type=" + type +
                '}';
    }
}
