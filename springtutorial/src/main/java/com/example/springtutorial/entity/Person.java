package com.example.springtutorial.entity;

import java.util.List;

import com.example.springtutorial.enums.AddressType;

import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
@ToString
public class Person {
	@Id
	@SequenceGenerator(name = "seq_person", allocationSize = 1)
	@GeneratedValue(generator = "seq_person", strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(length = 100, name = "name",nullable = false)
	private String name;
	@Column(length = 100, name = "surname",nullable = false)
	private String surname;
	// TODO null kontrolü yap
	@OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
//	@JoinColumn(name = "person_address_id")
	private List<Address> addressList;

	// TODO eager, cascade

	@Enumerated
	private AddressType addressType;

}
