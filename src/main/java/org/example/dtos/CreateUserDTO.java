package org.example.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class CreateUserDTO {

    @NotBlank(message = "user name cannot be blank")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "user cannot be under 18")
    @PositiveOrZero
    private Integer age;

    @PositiveOrZero(message = "Salary has to be greater than or equal to 0")
    private Double salary;

    @NotBlank(message = "Department is required")
    private String department;
}
