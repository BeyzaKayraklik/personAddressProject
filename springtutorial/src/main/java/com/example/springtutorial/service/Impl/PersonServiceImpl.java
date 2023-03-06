package com.example.springtutorial.service.Impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.springtutorial.dto.PersonDto;
import com.example.springtutorial.entity.Address;
import com.example.springtutorial.entity.Person;
import com.example.springtutorial.enums.AddressType;
import com.example.springtutorial.repositories.AddressRepositories;
import com.example.springtutorial.repositories.PersonRepositories;
import com.example.springtutorial.request.PersonRequest;
import com.example.springtutorial.service.PersonService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {


    private static final String OTHER_ADRESS = "other";
    // TODO AddressRepositories ayrı bir servis
    @Autowired
    private PersonRepositories personRepositories;


    @Override
    @Transactional
    public PersonDto save(PersonRequest request) {

        Person person = new Person();
        person.setId(request.getId());
        person.setName(request.getName());
        person.setSurname(request.getSurname());
        person.setAddressType(request.getAddressType());

        List<Address> addressList = new ArrayList<>();
        request.getAddressList().forEach(item -> {
            Address address = new Address();
            address.setAddress(item);
            address.setPerson(person);
            addressList.add(address);
        });

        person.setAddressList(addressList);
        Person personDb = personRepositories.save(person);
        return PersonDto.convertToPerson(personDb);
    }


    @Override
    public PersonDto update(Long id, PersonDto personDto) {
        Person personInDb = personRepositories.findById(id).orElseThrow(
                NullPointerException::new);

        personInDb.setName(personDto.getName());
        personInDb.setSurname(personDto.getSurname());
        personInDb.setAddressType(personDto.getAddressType());
        personInDb.getAddressList().clear();
//orphan-removal =true
        List<Address> addressList = new ArrayList<>();
        Person finalPerson = personInDb;
        personDto.getAddressList().forEach(item -> {
            Address address = new Address();
            address.setAddress(item);
            address.setPerson(finalPerson);
            addressList.add(address);
        });
        personInDb.setAddressList(addressList);
        personInDb = personRepositories.save(personInDb);
        personDto.setId(personInDb.getId());
        return personDto;
    }

    @Override
    public void delete(Long id) {
        personRepositories.deleteById(id);
    }

    @Override
    public PersonDto approve(Long id, PersonDto personDto) {
        Person personInDb = personRepositories.findById(id).orElseThrow(
                NullPointerException::new);
        personInDb.setName(personDto.getName());
        personInDb.setSurname(personDto.getSurname());
        personInDb.setAddressType(AddressType.APPROVED);
        personInDb.getAddressList().clear();
        List<Address> addressList = new ArrayList<>();
        Person finalPerson = personInDb;
        personDto.getAddressList().forEach(item -> {
            Address address = new Address();
            address.setAddress(item);

            address.setPerson(finalPerson);
            addressList.add(address);
        });
        personInDb.setAddressList(addressList);
        personInDb = personRepositories.save(personInDb);
        personDto.setId(personInDb.getId());
        return personDto;

    }

    @Override
    public List<PersonDto> getAll() {
        List<Person> personList = personRepositories.findAll();
        List<PersonDto> personDtos = new ArrayList<>();
        personList.forEach(person -> personDtos.add(PersonDto.convertToPerson(person)));
        return personDtos;
    }

    @Override
    public List<PersonDto> getAllAddress() {
        List<Person> personList = personRepositories.findAll();

        List<PersonDto> personDtoList = new ArrayList<>();

        personList.forEach(it -> {
            PersonDto personDto = new PersonDto();

            personDto.setAddressList(it.getAddressList().stream().map(Address::getAddress).filter(Objects::nonNull) // null
                    // değerleri
                    // gösterme
                    .collect(Collectors.toList()));
            personDtoList.add(personDto);
        });

        return personDtoList;
    }


    @Override
    public List<PersonDto> getAllOther() {
        List<Person> personList = personRepositories.findAll();
        List<PersonDto> personDtos = new ArrayList<>();
        personList.forEach(person -> {

            PersonDto personDto = new PersonDto();
            personDto.setId(person.getId());
            personDto.setName(person.getName());
            personDto.setSurname(person.getSurname());
            personDto.setAddressType(person.getAddressType());

            personDto.setAddressList(person.getAddressList().stream().filter(it -> {
                if (OTHER_ADRESS.equalsIgnoreCase(it.getAddress())) {
                    personDtos.add(personDto);
                }
                return false;
            }).map(Address::getAddress).collect(Collectors.toList()));
//            personDtos.add(personDto);
        });

//waitingden dönen true ise yeni bir liste açıp içine atmak daha mantıklı
        return personDtos;
    }

    @Override
    public List<PersonDto> getByAddressType(AddressType addressType) {

        List<Person> personList = personRepositories.findAll();
        List<PersonDto> personDtos = new ArrayList<>();
        personList.forEach(person -> {

            PersonDto personDto = new PersonDto();
            personDto.setId(person.getId());
            personDto.setName(person.getName());
            personDto.setSurname(person.getSurname());
            personDto.setAddressType(person.getAddressType());

            personRepositories.findByAddressType(AddressType.valueOf(String.valueOf(addressType)));

            if (person.getAddressType().equals(AddressType.valueOf(String.valueOf(addressType)))) {
                personDtos.add(personDto);
            }

            personDto.setAddressList(
                    person.getAddressList().stream().map(Address::getAddress).collect(Collectors.toList()));
        });
        return personDtos;
    }

}
