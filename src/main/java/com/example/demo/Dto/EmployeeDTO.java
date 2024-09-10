package com.example.demo.Dto;


import jakarta.validation.constraints.*;
import lombok.Data;


import java.time.LocalDate;
import java.util.List;

@Data
public class EmployeeDTO {

    @NotNull(message = "Employee ID is mandatory")
    @Pattern(regexp = "E[0-9]+", message = "Invalid Employee ID format")
    private String employeeId;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Phone numbers are mandatory")
    @Size(min = 1, message = "At least one phone number is required")
    private List<@Pattern(regexp = "[0-9]{10}", message = "Invalid phone number") String> phoneNumbers;

    @NotNull(message = "Date of joining is mandatory")
    private LocalDate doj;

    @Min(value = 0, message = "Salary must be a positive number")
    private Double salary;

    // Getters and Setters
}
