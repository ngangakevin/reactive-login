package org.example.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@ToString
@EqualsAndHashCode(of = {"id", "name", "department"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table("users")
public class Users {

    @Id
    private String id;
    private String name;
    private int age;
    private double salary;
    private String department;
}
