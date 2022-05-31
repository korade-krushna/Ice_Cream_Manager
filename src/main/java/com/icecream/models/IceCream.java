package com.icecream.models;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Icecream")
public class IceCream {
    // Ice Cream Model class
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Iid;
    @Column(unique = true)
    private String name;
    private String variant;

    private int quantity;
    private String image;
    @Column(length = 65500)
    @Size(min = 10, max = 65500)
    private String description;

    @ManyToOne
    private Employee employee;

    @Override
    public String toString() {
        return "IceCream{" +
                "Iid=" + Iid +
                ", name='" + name + '\'' +
                ", variant='" + variant + '\'' +
                ", quantity=" + quantity +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", employee=" + employee +
                '}';
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getIid() {
        return Iid;
    }

    public void setIid(int iid) {
        Iid = iid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
