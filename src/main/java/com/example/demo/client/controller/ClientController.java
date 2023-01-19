package com.example.demo.client.controller;

import com.example.demo.client.dto.ClientDetails;
import com.example.demo.client.email.MailSenderService;
import com.example.demo.client.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@RestController
//@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST,
//        RequestMethod.OPTIONS}, allowedHeaders = {"Content-Type", "X-Requested-With", "accept", "Origin",
//        "Access-Control-Request-Method", "Access-Control-Request-Headers", "Content-Length", "Server", "Date"},
//        exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"})
//@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {
@Autowired
ClientService service;
@Autowired
MailSenderService mailSenderService;

ClientDetails clientDetail;
Logger logger = LoggerFactory.getLogger(ClientController.class);

    @GetMapping("/findClient/{id}")
    public ResponseEntity<ClientDetails> findClientDetail(@PathVariable String id) {
        logger.info("Entered into ClientController-findClientDetail");
        clientDetail =  service.findClientDetail(id);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Description","Client Details Found")
                .body(clientDetail);
    }

    @GetMapping("/findAllClients")
    public ResponseEntity<List<ClientDetails>> findAllClientDetails() {
        logger.info("Entered into ClientController-findAllClientDetail");
        List<ClientDetails> clientDetails =  service.findAllClientDetails();
        return ResponseEntity.status(HttpStatus.OK)
                .header("Description","All Client Details Found")
                .body(clientDetails);
    }

    @GetMapping("/getClients")
    public ResponseEntity<List<String>> getAllClientDetails() {
        logger.info("Entered into ClientController-getAllClientDetails");
        List<String> clientDetails =  service.findAllClientDetails().stream().map(client -> client.getClientId())
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK)
                .header("Description","All Client Ids Found")
                .body(clientDetails);
    }

   @PostMapping(value = "/registerNewClient", consumes = {MediaType.APPLICATION_JSON_VALUE},
                 produces = {MediaType.APPLICATION_JSON_VALUE})
   public ResponseEntity<ClientDetails> insertClientDetails(@RequestBody ClientDetails client) throws IOException {
       logger.info("Entered into ClientController-insertClientDetails");

       clientDetail = service.addClient(client);
       if(clientDetail!=null) {
           String message = mailSenderService.sendMailForRegistration(client);
           System.out.println("EMail status" + message);
       }
       return ResponseEntity.status(HttpStatus.CREATED)
               .header("Description", "New Client Details inserted successfully")
               .body(clientDetail);
   }

    @PutMapping("/UpdateClient")
    public ResponseEntity<ClientDetails> updateClientDetails(@RequestBody ClientDetails details) {
        logger.info("Entered into ClientController-updateClientDetails");
        clientDetail = service.updateClient(details);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .header("Description","Client Details updated successfully")
                .body(clientDetail);
    }
    @DeleteMapping("/deleteClient/{id}")
    public ResponseEntity<ClientDetails> deleteClient(@PathVariable String id) {
        logger.info("Entered into ClientController-deleteClient ");
        clientDetail = service.removeClient(id);
        return ResponseEntity.status(HttpStatus.OK)
            .header("Description","Client deleted successfully")
            .body(clientDetail);
    }

    //adding new method
    //story 123

}

   /* @PostMapping(value = "/registerNewClient" ,consumes =  {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces =  {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ClientDetails> insertClientDetails(@RequestBody ClientDetails clientDetail,
                                                             @ModelAttribute RegisterFileUpload registerFileUpload) {



    public ResponseEntity<ClientDetails> insertClientDetails(@RequestParam(name = "clientId") String clientId,
                                                             @RequestParam(name = "user") String clientName,
                                                             @RequestParam(name = "clientName") String clientEmail)throws IOException
            //@RequestPart MultipartFile multipartFile*/
