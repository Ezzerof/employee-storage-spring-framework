package com.office.employees_storage_spring_framework.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PrivateEmployeeDTO extends EmployeeDTO{

    private String password;

    public PrivateEmployeeDTO(@NotEmpty @Pattern(regexp = "[A-Za-z]{2,3}", message = "title must contain only letters") String title,
                              @NotEmpty @Pattern(regexp = "[A-Za-z\\s]+", message = "first name must contain only letters and spaces") String firstName,
                              @NotEmpty @Pattern(regexp = "[A-Za-z]+", message = "second name must contain only letters") String secondName,
                              @NotEmpty @Pattern(regexp = "\\d{1,3}", message = "age should contain only alphanumeric characters") String age,
                              @NotEmpty @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Invalid email address") String email,
                              @NotEmpty @Pattern(regexp = "^[+]?\\d{10}$", message = "Phone number should be a 10-digit number") String phoneNumber,
                              @NotEmpty @Pattern(regexp = "^[a-zA-Z0-9]{6,12}$", message = "username must be of 6 to 12 length with no special characters") String username,
                              @NotEmpty String address,
                              @NotEmpty String country,
                              @NotEmpty String town,
                              @NotEmpty String picture,
                              String password) {
        super(title, firstName, secondName, age, email, phoneNumber, username, address, country, town, picture);
        this.password = password;
    }
}
