package com.example.springtutorial.repositories;

import com.example.springtutorial.dto.AddressDto;
import com.example.springtutorial.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepositories extends JpaRepository<Address, Long> {

}
