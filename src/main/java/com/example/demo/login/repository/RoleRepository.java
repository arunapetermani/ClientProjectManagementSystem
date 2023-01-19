package com.example.demo.login.repository;

import com.example.demo.login.dto.ERole;
import com.example.demo.login.dto.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Logger logger= LoggerFactory.getLogger("RoleRepository::::");
    Optional<Role> findByName(ERole name);
}