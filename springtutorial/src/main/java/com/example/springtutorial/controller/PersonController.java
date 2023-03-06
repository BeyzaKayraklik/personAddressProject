package com.example.springtutorial.controller;

import java.util.List;

import com.example.springtutorial.enums.AddressType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springtutorial.dto.PersonDto;
import com.example.springtutorial.request.PersonRequest;
import com.example.springtutorial.service.PersonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/person")

//@Api(value = "person document")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<PersonDto> save(@RequestBody PersonRequest request) {

        return ResponseEntity.ok(personService.save(request));
    }

    @PutMapping("/{id}")
//    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<PersonDto> update(@PathVariable Long id, @RequestBody PersonDto personDto) {
        PersonDto updatedPersonDto = personService.update(id, personDto);
        return ResponseEntity.ok(updatedPersonDto);
    }

    // TODO requestleri api bazlı ayır

    @PutMapping("/approve/{id}")
    public ResponseEntity<PersonDto> approved(@PathVariable Long id, @RequestBody PersonDto personDto) {
        PersonDto approvedPersonDto = personService.approve(id, personDto);
        return ResponseEntity.ok(approvedPersonDto);
    }


    // TODO AdressType enumını dışarıdan almak gerek

    @GetMapping
    public ResponseEntity<List<PersonDto>> getAll() {
        return ResponseEntity.ok(personService.getAll());

    }

    @GetMapping("/address")
    public ResponseEntity<List<PersonDto>> getAllAddress() {
        return ResponseEntity.ok(personService.getAllAddress());

    }

    @GetMapping("/other")
    public ResponseEntity<List<PersonDto>> getAllOther() {
        return ResponseEntity.ok(personService.getAllOther());
    }

    @GetMapping("/find/{type}")
    public ResponseEntity<List<PersonDto>> getByAddressType(@PathVariable("type") AddressType addressType) {
        return ResponseEntity.ok(personService.getByAddressType(addressType));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.ok().build();
    }
}
