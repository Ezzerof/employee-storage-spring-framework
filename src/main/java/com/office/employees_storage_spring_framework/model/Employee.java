package com.office.employees_storage_spring_framework.model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // .AUTO will autoincrement ID but with random numbers
    @Column(nullable = false, updatable = false) // will prohibit null value for empId and will not allow to update it
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String secondName;
    @Column(nullable = false)
    private String age;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String town;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    private String picture;

    public Employee(){}

    public Employee(String title, String firstName, String secondName, String age, String email, String phoneNumber, String address, String country, String town, String username, String password, String picture) {
        this.title = title;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.country = country;
        this.town = town;
        this.username = username;
        this.password = password;
        this.picture = picture;
    }
}
