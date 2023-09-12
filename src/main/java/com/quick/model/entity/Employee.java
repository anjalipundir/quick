package com.quick.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    /*
    * Here are some factors to consider when making the decision:
    Performance: Identity columns are typically faster than sequences, because the database does not need to access the sequence object to generate the next value.
    Scalability: Sequences can be more scalable than identity columns, because they can be used to generate a large number of values without affecting the performance of the database.
    Flexibility: Sequences offer more flexibility than identity columns, because you can control the values that are generated by the sequence.
    **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int salary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deptId")
    private Department department;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<Address> addresses;

}
