package com.example.springtutorial.repositories;

import com.example.springtutorial.dto.AddressDto;
import com.example.springtutorial.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressRepositories extends JpaRepository<Address, Long> {

}
