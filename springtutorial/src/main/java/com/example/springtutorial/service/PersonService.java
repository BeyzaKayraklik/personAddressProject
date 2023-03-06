package com.example.springtutorial.service;

import java.util.List;

import com.example.springtutorial.enums.AddressType;
import org.springframework.data.domain.Page;

import com.example.springtutorial.dto.PersonDto;
import com.example.springtutorial.request.PersonRequest;

public interface PersonService {
    PersonDto save(PersonRequest request);

    PersonDto update(Long id, PersonDto personDto);

    void delete(Long id);

    PersonDto approve(Long id, PersonDto personDto);

    List<PersonDto> getAll();

    List<PersonDto> getAllAddress();

    List<PersonDto> getAllOther();

    List<PersonDto> getByAddressType(AddressType addressType);

}
