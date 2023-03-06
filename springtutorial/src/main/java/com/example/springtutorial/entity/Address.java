package com.example.springtutorial.entity;

import com.example.springtutorial.enums.AddressType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
//id ve entityi kes unutma.
@Entity
@Table(name="person_address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
//iki adresin idsi eşitse bu iki nesne aynı nesnedir.
@ToString

public class Address implements Serializable {
    @Id
    //primary key @ıd olarak atanmalı ve autogenerated olamlı
    @SequenceGenerator(name = "seq_person_address",allocationSize = 1)
    //allocation size artış miktarı
    @GeneratedValue(generator = "seq_person_address",strategy = GenerationType.SEQUENCE)
    //veritabanında ilk yaratılırken bununla yaratılır. sequencedaki veriyi alıp atar.

    private Long id;
    private String address;


//  (fetch = )
    //veritabanından her select edildiğinde ilgili kişisi de mutlaka gelsin
//    lazy=sadece getter edilirse get select
    @ManyToOne
    @JoinColumn(name = "person_address_id")
    private Person person;

//    @Enumerated
//    private AddressType addressType;

    //@Column içerisinde öz belirlenebilir.
    //lenght = default size 255 uzunsa değiştirilmeli.
    //nullable =true,false
//    public enum AddressType{
//        WAITING,
//        APPROVED,
//
//    };


}
