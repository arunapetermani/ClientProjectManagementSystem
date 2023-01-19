package com.example.demo.client.service;

import com.example.demo.client.dto.ClientDetails;
import com.example.demo.client.exception.ClientNotFoundException;
import com.example.demo.client.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ClientServiceTest {

    @InjectMocks
    ClientService serviceUnderTest;

    @Mock
    ClientRepository mockClientRepository;

    List<ClientDetails> mockClientDetails = new ArrayList<>();


    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        ClientDetails client1 = new ClientDetails();
        client1.setClientId("C101");
        client1.setClientEmail("abc@xyz.com");
        client1.setFileName("sampleFile.pdf");

        ClientDetails client2 = new ClientDetails();
        client2.setClientId("C102");
        client2.setClientEmail("def@xyz.com");
        client2.setFileName("sampleFile1.pdf");

        mockClientDetails.add(client1);
        mockClientDetails.add(client2);
        when(mockClientRepository.findAll()).thenReturn(mockClientDetails);
    }


    @Test
    void findClientDetail_failure() {
        when(mockClientRepository.findAll()).thenThrow(new ClientNotFoundException());
        Assertions.assertThrows(ClientNotFoundException.class, () -> serviceUnderTest.findAllClientDetails());
    }

    @Test
    void findAllClientDetails_success() {
        List<ClientDetails> result =
                serviceUnderTest.findAllClientDetails();
        Assertions.assertEquals(mockClientDetails, result);
    }

    @Test
    void findAllClientDetails_failure() {

        mockClientDetails.clear();
         Assertions.assertThrows(ClientNotFoundException.class, () -> serviceUnderTest.findAllClientDetails());
    }

    @Test
    void addClient() {
    }

    @Test
    void updateClient() {
    }

    @Test
    void removeClient() {
    }
}