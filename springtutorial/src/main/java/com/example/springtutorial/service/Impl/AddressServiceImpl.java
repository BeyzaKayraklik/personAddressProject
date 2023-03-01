package com.example.springtutorial.service.Impl;

import com.example.springtutorial.dto.AddressDto;
import com.example.springtutorial.entity.Address;
import com.example.springtutorial.repositories.AddressRepositories;
import com.example.springtutorial.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepositories addressRepositories;

    @Override
    public AddressDto getAddressById(Long id) {
        Optional<Address> addressOptional = addressRepositories.findById(id);
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            AddressDto addressDto = new AddressDto();
            addressDto.setId(address.getId());
            addressDto.setAddress(address.getAddress());
            return addressDto;
        } else {
            throw new IllegalArgumentException("Address not found with ID: " + id);
        }
    }
}
