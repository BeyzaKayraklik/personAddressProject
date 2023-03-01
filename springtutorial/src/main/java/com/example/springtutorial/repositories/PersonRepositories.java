package com.example.springtutorial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springtutorial.entity.Person;
import com.example.springtutorial.enums.AddressType;

@Repository
public interface PersonRepositories extends JpaRepository<Person, Long> {

	List<Person> findByAddressType(AddressType addressType);

}
