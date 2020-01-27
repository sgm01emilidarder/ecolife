package com.ecolife.dto;

import java.util.Objects;

public class User {
    private int id;
    private String dni;
    private String name;
    private String surname;
    private String username;
    private String password;
    private int phone;
    private String email;

    public User(int id) {
        this.id = id;
    }

    public User(String dni, String name, String surname, String username, String password, int phone, String email) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public User(int id, String dni, String name, String surname, String username, String password, int phone, String email) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                dni.equals(user.dni) &&
                username.equals(user.username) &&
                email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dni, username, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                '}';
    }
}
