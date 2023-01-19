package com.example.demo.project.service;

import com.example.demo.client.dto.ClientDetails;
import com.example.demo.client.exception.ProjectNotFoundException;
import com.example.demo.project.dto.Project;
import com.example.demo.project.dto.ProjectMapping;
import com.example.demo.client.email.MailSenderService;
import com.example.demo.client.exception.ClientProjectNotFoundException;
import com.example.demo.project.repository.ProjectMappingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectMappingService {
    @Autowired
    ProjectMappingRepository repository;
    @Autowired
    MailSenderService mailSenderService;
    List<Project> projectList ;

    Logger logger = LoggerFactory.getLogger(ProjectMappingService.class);


    public ProjectMapping addProjectWithClient(ProjectMapping mapping) {
        logger.info("Entered ProjectMappingService addProjectWithClient",mapping);
        try {
            repository.save(mapping);
        }
        catch (Exception e) {
            throw new ClientProjectNotFoundException();
        }
        return mapping;
    }

    public ProjectMapping findClientProjectDetail(String projectId) {
        logger.info("Enter into ProjectMappingService-findClientProjectDetail method",projectId);
        return repository.findById(projectId)
                .orElseThrow(ProjectNotFoundException::new);
    }

    public List<Project> findAllClientProjectDetails() {
        List<ProjectMapping> details = repository.findAll();
        if(details == null) {
            System.out.println("No records found");
            throw new ProjectNotFoundException();
        }
        return getProject(details);
    }
    public ProjectMapping updateClientProject(@RequestBody ProjectMapping projectMapping) {
        logger.info("Enter into ProjectMappingService-updateClientProject",projectMapping);
        return repository.findById(projectMapping.getProjectId())
                .map(oldProjecttDetails -> {
                    ClientDetails clientDetails = new ClientDetails();
                    oldProjecttDetails.setProjectId(projectMapping.getProjectId());
                    //oldProjecttDetails.setClientId(projectMapping.getClientId());
                    oldProjecttDetails.setClientDetails(projectMapping.getClientDetails());
                    oldProjecttDetails.setProjectStatus(projectMapping.getProjectStatus());
                    oldProjecttDetails.setDate(projectMapping.getDate());
                    repository.save(oldProjecttDetails);
                    return oldProjecttDetails;

                })
                .orElseThrow(() -> new ClientProjectNotFoundException());
    }
     public ProjectMapping removeClientProject(String projectId) {
        logger.info("Enter into ProjectMappingService-removeClientProject",projectId);
        Optional<ProjectMapping> projectMapping=repository.findById(projectId);
        if(projectMapping.isEmpty())
            throw new ClientProjectNotFoundException();
        else
            repository.deleteById(projectId);
        return projectMapping.get();
    }
    private List<Project> getProject(List<ProjectMapping> projectMapping) {
        Project project;
        projectList = new ArrayList<Project>() ;
        for ( int i=0;i<projectMapping.size();i++) {
            project = new Project();
            project.setClientId( projectMapping.get(i).getClientDetails().getClientId());
            project.setProjectId(projectMapping.get(i).getProjectId());
            project.setProjectStatus(projectMapping.get(i).getProjectStatus());
            project.setDate(projectMapping.get(i).getDate());
            projectList.add(project);
        }
        return projectList;
    }

}
