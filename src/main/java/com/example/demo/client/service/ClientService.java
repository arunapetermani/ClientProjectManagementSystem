package com.example.demo.client.service;

import com.example.demo.client.dto.ClientDetails;
import com.example.demo.client.exception.ClientNotFoundException;
import com.example.demo.client.repository.ClientRepository;
import com.example.demo.client.vaidation.ValidationUtil;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Service
public class ClientService {
    @Autowired
    ClientRepository repository;

    Logger logger = LoggerFactory.getLogger(ClientService.class);

    public ClientDetails findClientDetail(String id) {
        logger.info("Entered Service-findClientDetailsById");
        return repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException());
    }

    public List<ClientDetails> findAllClientDetails() {

        logger.info("Entered Service-findAllClientDetails");
        List<ClientDetails> clientDetails = repository.findAll();
        if (clientDetails.isEmpty()) {
            throw new ClientNotFoundException();
        }
        return clientDetails;
    }

    public ClientDetails addClient(ClientDetails client) throws FileUploadException {
        logger.info("Inside of Service-addClient method");
        return repository.save(client);
    }

    public ClientDetails updateClient(@RequestBody ClientDetails client) {
        logger.info("Inside of Service-updateClient method");
        return repository.findById(client.getClientId())
                .map(oldClientDetails -> {
                    oldClientDetails.setClientId(client.getClientId());
                    oldClientDetails.setClientName(client.getClientName());
                    oldClientDetails.setClientEmail(client.getClientEmail());
                    repository.save(oldClientDetails);
                    return oldClientDetails;

                })
                .orElseThrow(() -> new ClientNotFoundException());
    }

    public ClientDetails removeClient(String id) {
        logger.info("Entered Service-removeClient method");
        Optional<ClientDetails> clientDetail = repository.findById(id);
        if (clientDetail.isEmpty())
            throw new ClientNotFoundException();
        else
            repository.deleteById(id);
        System.out.println("=========" + clientDetail.get());
        return clientDetail.get();
    }
}

   /* public ClientDetails addClient(ClientDetails client) throws FileUploadException {
        boolean flag=ValidationUtil.validateIsPDF(client.getFileName());
        if(flag)
        return repository.save(client);
        else
            throw new FileUploadException();
    }*/

