package org.example.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class CreateUserDTO {

    @NotBlank
    private String name;

    @NotNull
    @Min(18)
    @PositiveOrZero
    private Integer age;

    @NotNull
    @PositiveOrZero
    private Double salary;

    @NotBlank
    private String department;
}
