package org.example.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class UpdateUserDTO {

    @PositiveOrZero
    @Min(18)
    private Integer age;

    @PositiveOrZero
    private Double salary;
}
