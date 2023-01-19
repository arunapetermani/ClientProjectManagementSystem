package com.example.demo.project.controller;

import com.example.demo.client.controller.ClientController;
import com.example.demo.client.dto.ClientDetails;
import com.example.demo.client.email.MailSenderService;
import com.example.demo.client.exception.ProjectNotFoundException;
import com.example.demo.client.service.ClientService;
import com.example.demo.project.dto.Project;
import com.example.demo.project.dto.ProjectMapping;
import com.example.demo.project.service.ProjectMappingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProjectMappingControllerTest {

    @InjectMocks
    ProjectMappingController projectMappingController;

    @Mock
    ProjectMappingService service;

    @Mock
    ProjectMapping mapping;

    String mockProjectId;
    ProjectMapping mockProjectMapping;
    Project mockProject;
    List<Project> mockListProject;

    List<ClientDetails> mockClientList;

    ResponseEntity<Project> mockResponseEntity;
    ResponseEntity<List<Project>> listMockResponseEntity;


    @BeforeEach
    public void init() {

        MockitoAnnotations.openMocks(this);
        mockProjectMapping = new ProjectMapping();
        mockProject = new Project();
        mockProjectId = "P101";
        mockProjectMapping.setProjectId("P101");
        mockProjectMapping.setProjectStatus("Active");
        mockProjectMapping.setDate("01/01/2022");
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setClientId("C101");
        mockProjectMapping.setClientDetails(clientDetails);

        mockProject.setProjectId("P101");
        mockProject.setProjectStatus("Active");
        mockProject.setDate("01/01/2022");
        mockProject.setClientId("C101");

        Project project1 = new Project();
        project1.setProjectId("P101");
        project1.setProjectStatus("Active");
        project1.setDate("01/01/2022");
        project1.setClientId("C101");

        List<Project> projectList = new ArrayList<>();
        projectList.add(mockProject);
        projectList.add(project1);

        mockResponseEntity = ResponseEntity.status(HttpStatus.OK)
                .header("Description","Client Details Found")
                .body(mockProject);
        listMockResponseEntity = ResponseEntity.status(HttpStatus.OK)
                .header("Description","All Client Project Details Found")
                .body(projectList);
        when(service.findClientProjectDetail(mockProjectId)).thenReturn(mockProjectMapping);

    }

    @Test
    void findClientDetail_success() {
        ResponseEntity<Project> result = projectMappingController.findClientDetail(mockProjectId);
        assertAll(() -> assertEquals(mockProject.getProjectId(), result.getBody().getProjectId()),
                () -> assertEquals(mockProject.getProjectStatus(), result.getBody().getProjectStatus()),
                () -> assertEquals(mockProject.getClientId(), result.getBody().getClientId()),
                () -> assertEquals(mockProject.getDate(), result.getBody().getDate()),
                () -> assertEquals(mockResponseEntity.getHeaders().get("Description"), result.getHeaders().get("Description")));
    }

    @Test
    void findClientDetail_failure() {
        when(service.findClientProjectDetail(mockProjectId)).thenThrow(new ProjectNotFoundException());
        assertThrows(ProjectNotFoundException.class, () -> projectMappingController.findClientDetail(mockProjectId));


    }

    @Test
    void findAllClientProjectDetails() {
        when(service.findAllClientProjectDetails()).thenReturn(mockListProject);
        ResponseEntity<List<Project>> result = projectMappingController.findAllClientProjectDetails();
        assertAll(() -> assertEquals(mockListProject, result.getBody()),
                () -> assertEquals(listMockResponseEntity.getHeaders().get("Description"),
                        result.getHeaders().get("Description")));
    }

    @Test
    void findAllClientProjectDetails_failure() {
        when(service.findAllClientProjectDetails()).thenThrow(new ProjectNotFoundException());
        assertThrows(ProjectNotFoundException.class, () -> projectMappingController.findAllClientProjectDetails());
    }

    @Test
    void addProjectWithClient() {
    }

    @Test
    void deleteProject() {
    }

    @Test
    void updateProjectDetails() {
    }
}