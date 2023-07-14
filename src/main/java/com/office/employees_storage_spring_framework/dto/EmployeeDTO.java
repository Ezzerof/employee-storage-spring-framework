package com.office.employees_storage_spring_framework.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmployeeDTO {
    @NotEmpty
    @Pattern(regexp = "[A-Za-z]{2,3}",
            message = "title must contain only letters between 2 to 3")
    private String title;
    @NotEmpty
    @Pattern(regexp = "[A-Za-z\\s]+",
            message = "first name must contain only letters and spaces")
    private String firstName;
    @NotEmpty
    @Pattern(regexp = "[A-Za-z]+",
            message = "second name must contain only letters")
    private String secondName;
    @NotEmpty
    @Pattern(regexp = "\\d{1,3}", message = "age should contain only alphanumeric characters")
    private String age;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Invalid email address")
    private String email;
    @NotEmpty
    @Pattern(regexp = "^[+]?\\d{10}$", message = "Phone number should be a 10-digit number")
    private String phoneNumber;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9]{6,12}$",
            message = "username must be of 6 to 12 length with no special characters")
    private String username;
    @NotEmpty
    private String address;
    @NotEmpty
    private String country;
    @NotEmpty
    private String town;
    @NotEmpty
    private String picture;


}
