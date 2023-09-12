package com.quick.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {

    private String streetAddress;
    private String streetAddress2;
    private String city;
    private String state;
    private String country;
    private int pinCode;

}
