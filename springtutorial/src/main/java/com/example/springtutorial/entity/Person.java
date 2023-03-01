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
//@ApiModel(value = "person model ", description= "my person model ")
//jpa annotations
public class Person {

	@Id
	@SequenceGenerator(name = "seq_person", allocationSize = 1)
	@GeneratedValue(generator = "seq_person", strategy = GenerationType.SEQUENCE)
	private Long id;
//    @ApiModelProperty(value = "person address property")
	// kalıtım kullanılırsa parent tarzı özelleştirmeleri var .

	@Column(length = 100, name = "name",nullable = false)
	private String name;
	@Column(length = 100, name = "surname",nullable = false)
	private String surname;
	@OneToMany(mappedBy = "person",cascade = CascadeType.ALL,orphanRemoval = true)
//	@JoinColumn(name = "person_address_id")
	private List<Address> addressList;

	// TODO eager, cascade

	@Enumerated
	private AddressType addressType;

}
