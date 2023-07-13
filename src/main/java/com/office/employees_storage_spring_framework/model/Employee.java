package com.office.employees_storage_spring_framework.model;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // .AUTO will autoincrement ID but with random numbers
    @Column(nullable = false, updatable = false) // will prohibit null value for empId and will not allow to update it
    private long id;
    private String firstName;
    private String secondName;
    private String email;
    private String phoneNumber;
    private String address;
    private String town;

    public Employee(long id, String firstName, String secondName, String email, String phoneNumber, String address, String town) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.town = town;
    }

    public Employee() {
    }

    public long getEmpId() {
        return id;
    }

    public void setEmpId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Override
    public String toString() {
        return "\nID: " + getEmpId() +
                ", First name: " + getFirstName() +
                ", Second name: " + getSecondName() +
                ", Email: " + getEmail() +
                ", Phone number: " + getPhoneNumber() +
                ", Address: " + getAddress() +
                ", Town: " + getTown();
    }
}
