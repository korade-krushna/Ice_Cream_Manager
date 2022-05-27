package com.icecream.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "Employee")
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String roll;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<IceCream> flavours = new ArrayList<>();

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
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }
}
