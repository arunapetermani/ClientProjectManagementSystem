package com.example.demo.project.repository;

import com.example.demo.project.dto.ProjectMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMappingRepository extends JpaRepository<ProjectMapping,String> {

}
