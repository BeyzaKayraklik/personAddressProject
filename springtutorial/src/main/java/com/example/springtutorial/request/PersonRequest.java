package com.example.springtutorial.request;

import java.util.List;

import com.example.springtutorial.enums.AddressType;

import lombok.Data;

@Data
public class PersonRequest {
//	@NotBlank
//	@Size(max = 2000)
	// TODO
	private Long id;
	private String name;
	private String surname;
//    private List<Address> addressList;

	private AddressType addressType;
	private List<String> addressList;

	// NotBlank
}
