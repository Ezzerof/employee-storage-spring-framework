package com.office.employees_storage_spring_framework.model;

public class Employee {


    private int empId;
    private String firstName;
    private String secondName;
    private String email;
    private int phoneNumber;
    private String address;
    private String town;

    public Employee(int empId, String firstName, String secondName, String email, int phoneNumber, String address, String town) {
        this.empId = empId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.town = town;
    }


    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
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
