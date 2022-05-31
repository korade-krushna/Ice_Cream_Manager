package com.icecream.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "Employee")
@Entity
public class Employee {
    // Employee Model class
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean enabled;
    private String role;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
    private List<IceCream> flavours = new ArrayList<>();

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roll='" + role + '\'' +
                ", flavours=" + flavours +
                '}';
    }

    public List<IceCream> getFlavours() {
        return flavours;
    }

    public void setFlavours(List<IceCream> flavours) {
        this.flavours = flavours;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employee() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoll() {
        return role;
    }

    public void setRoll(String role) {
        this.role = role;
    }
}
