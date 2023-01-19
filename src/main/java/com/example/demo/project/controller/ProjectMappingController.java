package com.example.demo.project.controller;

import com.example.demo.client.dto.ClientDetails;
import com.example.demo.project.dto.Project;
import com.example.demo.project.dto.ProjectMapping;
import com.example.demo.project.service.ProjectMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProjectMappingController {
    @Autowired
    ProjectMappingService service;
    ProjectMapping mapping;

    Logger logger = LoggerFactory.getLogger(ProjectMappingController.class);

   @GetMapping("/findClientWithProject/{id}")
    public ResponseEntity<Project> findClientDetail(@PathVariable String id) {
        mapping =  service.findClientProjectDetail(id);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Description","Client Details Found")
                .body(getProject(mapping));
    }

    @GetMapping("/findAllClientWithProjects")
    public ResponseEntity<List<Project>> findAllClientProjectDetails() {
        logger.info("Inside of ProjectMappingController-findAll method");
        List<Project> projectList =  service.findAllClientProjectDetails();
      /*  projectMappingList.forEach(projectMapping -> {
          String clientId =  projectMapping.getClientDetails().getClientId();
        });*/
        return ResponseEntity.status(HttpStatus.OK)
                .header("Description","All Client Project Details Found")
                .body(projectList);
    }

    @PostMapping("/registerProject")
    public ResponseEntity<ProjectMapping> addProjectWithClient(@Valid @RequestBody Project project) {
       logger.info("Entered into ProjectMappingController-addProjectWithClient method",project);
        ProjectMapping projectMapping = getProjectMapping(project);
        mapping = service.addProjectWithClient(projectMapping);
        logger.info("New project added",mapping);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Description","project details inserted successfully")
                .body(mapping);


    }

    @DeleteMapping("/deleteProject/{id}")
    public ResponseEntity<ProjectMapping> deleteProject(@PathVariable String id) {
        logger.info("Entered into ProjectMappingController-deleteProject ");
        mapping = service.removeClientProject(id);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Description","Client deleted successfully")
                .body(mapping);
    }
    @PutMapping("/UpdateProject")
    public ResponseEntity<Project> updateProjectDetails(@RequestBody Project project) {
        logger.info("Entered into ProjectMappingController-UpdateProject ");
        ProjectMapping projectMapping = getProjectMapping(project);
        mapping = service.updateClientProject(projectMapping);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .header("Description","Client Details updated successfully")
                .body(getProject(mapping));
    }

    private ProjectMapping getProjectMapping(Project project) {
        ProjectMapping projectMapping = new ProjectMapping();
        ClientDetails details = new ClientDetails();
        projectMapping.setProjectId(project.getProjectId());
        projectMapping.setProjectStatus(project.getProjectStatus());
        projectMapping.setDate(project.getDate());
        details.setClientId(project.getClientId());
        projectMapping.setClientDetails(details);
        return projectMapping;
    }

    private Project getProject(ProjectMapping projectMapping) {
        Project project = new Project();
        project.setProjectId(projectMapping.getProjectId());
        project.setProjectStatus(projectMapping.getProjectStatus());
        project.setDate(projectMapping.getDate());
        project.setClientId(projectMapping.getClientDetails().getClientId());
        return project;
    }
}
