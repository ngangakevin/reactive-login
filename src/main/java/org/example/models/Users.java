package org.example.models;

import lombok.*;
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
    private String name;
    private int age;
    private double salary;
    private String department;
}
