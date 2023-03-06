package com.example.springtutorial.dto;

import java.util.List;

import com.example.springtutorial.entity.Address;
import com.example.springtutorial.entity.Person;
import com.example.springtutorial.enums.AddressType;

import lombok.Data;

@Data
public class PersonDto {
	private Long id;
	private String name;
	private String surname;
	private AddressType addressType;
	private List<String> addressList;

	public static PersonDto convertToPerson(Person person) {

		PersonDto dto = new PersonDto();
		dto.addressList = person.getAddressList().stream().map(Address::getAddress).toList();
		dto.addressType = person.getAddressType();
		dto.name = person.getName();
		dto.surname = person.getSurname();
		dto.id = person.getId();
		return dto;

	}

}
