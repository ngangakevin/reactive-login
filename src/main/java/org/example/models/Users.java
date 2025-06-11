package org.example.models;

import com.mongodb.lang.Nullable;
import lombok.*;
import org.example.dtos.CreateUserDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@EqualsAndHashCode(of = {"id", "name", "department"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(value = "users")
public class Users {

    @Id
    private String id;
    @NonNull
    private String name;

    @Nullable
    private Integer age;

    @Nullable
    private Double salary;

    @NonNull
    private String department;

    public static Users fromDTO(CreateUserDTO dto){
        return new Users(null, dto.getName(), dto.getAge(), dto.getSalary(), dto.getDepartment());
    }
}
