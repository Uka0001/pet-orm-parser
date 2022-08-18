package org.uka0001;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class Person {
    private String name;
    private int age;
    private int salary;
    private String position;
    private LocalDate dateOfBirth;

}
