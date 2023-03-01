package com.example.springtutorial.dto;

import com.example.springtutorial.enums.AddressType;
import lombok.Data;


@Data
public class AddressDto {

    private Long id;
    private String address;

//    private AddressType addressType;
    private Boolean status;


}
