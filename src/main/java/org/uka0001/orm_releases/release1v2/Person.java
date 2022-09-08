package org.uka0001.orm_releases.release1v2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class Person {

    private String name;
    private BigInteger age;
    private BigInteger salary;
    private String position;
    private LocalDate dateOfBirth;
    private Float xxx;

}