package com.example.demo.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientProjectDetails {
    private int clientId;
    private String clientName;
    private String projectId;
    private String projectStatus;

   /* public ClientProjectDetails(ProjectMapping mapping,ClientDetails details) {
        clientId = details.getClientId();
        clientName = details.getClientName();
        projectId = mapping.getProjectId();
        projectStatus = mapping.getProjectStatus();
    }*/


}
