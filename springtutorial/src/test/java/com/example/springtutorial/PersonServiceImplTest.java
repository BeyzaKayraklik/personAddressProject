package com.example.springtutorial;

import com.example.springtutorial.dto.PersonDto;
import com.example.springtutorial.entity.Person;
import com.example.springtutorial.enums.AddressType;
import com.example.springtutorial.repositories.AddressRepositories;
import com.example.springtutorial.repositories.PersonRepositories;
import com.example.springtutorial.request.PersonRequest;
import com.example.springtutorial.service.Impl.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//mockito:nesnenin instancesi test edilecek ise yaratırken kullanılır.
//junit testing her küçük parçanın kodlarla test edilmesi : tüm testler kısa sürede yapılır.

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {


    @InjectMocks
    private PersonServiceImpl personService;
    @Mock
    private PersonRepositories personRepositories;
    @Mock
    private AddressRepositories addressRepositories;

    @Test
    public void testSave() {
        PersonRequest personDto = new PersonRequest();
//        personDto.setId(1L);
        personDto.setName("Test-Name");
        personDto.setSurname("Test-Surname");
        personDto.setAddressType(AddressType.APPROVED);
        personDto.setAddressList(List.of("Test-Address-1"));
        Person personMock = Mockito.mock(Person.class);


        Mockito.when(personMock.getId()).thenReturn(1L);
        Mockito.when(personRepositories.save(ArgumentMatchers.any(Person.class))).thenReturn(personMock);
        PersonDto result = personService.save(personDto);

        //1numaralı veri oluşur.
        //gönderdiğim nesne ile gelen nesne aynı olmalı
        //ilk aşamada mocklanmadığı için boş döner nullpointerexc

//        assertEquals(result.getName(), personDto.getName());
        assertEquals(result.getId(), 1L);
    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void approve() {
    }

    @Test
    void getAllAddress() {
    }

    @Test
    void getByAddressType() {
    }

    @Test
    void testGetAll() {
    }
}