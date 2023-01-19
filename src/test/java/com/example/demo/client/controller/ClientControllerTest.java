package com.example.demo.client.controller;

import com.example.demo.client.dto.ClientDetails;
import com.example.demo.client.email.MailSenderService;
import com.example.demo.client.exception.ClientNotFoundException;
import com.example.demo.client.repository.ClientRepository;
import com.example.demo.client.service.ClientService;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ClientControllerTest {
    @InjectMocks
    ClientController controller;

    @Mock
    ClientService service;

    @Mock
    MailSenderService mockMailSenderService;

    String mockClientId;
    ClientDetails mockClient;
    List<ClientDetails> mockClientList;

    ResponseEntity<ClientDetails> mockResponseEntity;
    ResponseEntity<List<ClientDetails>> listMockResponseEntity;


    @BeforeEach
    public void init() {

        MockitoAnnotations.openMocks(this);
        mockClientId = "C101";
        mockClient = new ClientDetails();
        mockClient.setClientId("C101");
        mockClient.setClientEmail("abc@xyz.com");
        mockClient.setFileName("sampleFile.pdf");
        mockResponseEntity = ResponseEntity.status(HttpStatus.OK)
                .header("Description","Client Details Found")
                .body(mockClient);
        when(mockMailSenderService.sendMailForRegistration(mockClient)).thenReturn("success");

    }

    @Test
    public void findClientDetail_success() {
        when(service.findClientDetail(mockClientId)).thenReturn(mockClient);

        ResponseEntity<ClientDetails> result = controller.findClientDetail(mockClientId);
        Assertions.assertEquals(mockResponseEntity, result);

    }

    @Test
    public void findClientDetail_failure() {

        when(service.findClientDetail(mockClientId)).thenThrow(new ClientNotFoundException());
        assertThrows(ClientNotFoundException.class, () -> controller.findClientDetail(mockClientId));

    }
    @Test
    public void findAllClientDetails_success() {
        ClientDetails mockClient1 = new ClientDetails();
        mockClient1.setClientId("C101");
        mockClient1.setClientEmail("abc@xyz.com");
        mockClient1.setFileName("sampleFile.pdf");

        ClientDetails mockClient2 = new ClientDetails();
        mockClient2.setClientId("C102");
        mockClient2.setClientEmail("abc2@xyz.com");
        mockClient2.setFileName("sampleFile2.pdf");

        ClientDetails mockClient3 = new ClientDetails();
        mockClient3.setClientId("C103");
        mockClient3.setClientEmail("abc3@xyz.com");
        mockClient3.setFileName("sampleFile3.pdf");

        mockClientList = new ArrayList<ClientDetails>();
        mockClientList.add(mockClient1);
        mockClientList.add(mockClient2);
        mockClientList.add(mockClient3);

        listMockResponseEntity = ResponseEntity.status(HttpStatus.OK)
                .header("Description","All Client Details Found")
                .body(mockClientList);

        when(service.findAllClientDetails()).thenReturn(mockClientList);
        ResponseEntity<List<ClientDetails>> result =controller.findAllClientDetails();
        Assertions.assertEquals(listMockResponseEntity,result);
    }
    @Test
    public void findAllClientDetails_failure() {
        when(service.findAllClientDetails()).thenThrow(new ClientNotFoundException());
        assertThrows(ClientNotFoundException.class, () -> controller.findAllClientDetails());

    }
    @Test
    public void insertClientDetails_success() throws IOException {
        ResponseEntity<ClientDetails> mockResponse =ResponseEntity.status(HttpStatus.CREATED)
                .header("Description", "New Client Details inserted successfully")
                .body(mockClient);
        when(service.addClient(mockClient)).thenReturn(mockClient);
        ResponseEntity<ClientDetails> result = controller.insertClientDetails(mockClient);
        verify(mockMailSenderService).sendMailForRegistration(mockClient);
        Assertions.assertEquals(mockResponse, result);

    }

    @Test
    public void insertClientDetails_failure() throws IOException {
        ResponseEntity<ClientDetails> mockResponse =ResponseEntity.status(HttpStatus.CREATED)
                .header("Description", "New Client Details inserted successfully")
                .body(mockClient);
        when(service.addClient(mockClient)).thenThrow(new FileUploadException());
        assertThrows(FileUploadException.class, () -> controller.insertClientDetails(mockClient));
        verify(service, times(1)).addClient(mockClient);

        verify(mockMailSenderService, times(0)).sendMailForRegistration(mockClient);

    }

}
