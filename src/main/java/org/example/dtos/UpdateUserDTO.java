package org.example.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class UpdateUserDTO {

    @NotBlank
    @NotEmpty
    private String name;

    @PositiveOrZero
    @Min(18)
    private Integer age;

    @PositiveOrZero
    private Double salary;

    @NotBlank
    @NotEmpty
    private String department;
}
