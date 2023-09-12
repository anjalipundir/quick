package com.quick.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quick.model.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Access(AccessType.FIELD)
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String streetAddress;
    private String streetAddress2;
    private String city;
    private String state;
    private String country;
    private int pinCode;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empId")
    @JsonIgnore
    private Employee employee;

}
