package com.example.springtutorial.service;

import com.example.springtutorial.dto.AddressDto;
import com.example.springtutorial.entity.Address;

public interface AddressService {

  AddressDto getAddressById(Long id);
}
