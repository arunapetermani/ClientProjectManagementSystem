package com.example.demo.login.dto;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Getter
@Setter
public class JwtResponse implements Serializable {
    private Long id;
    private String jwt;
    private String userName;
    private String email;
    private String role;

    public JwtResponse(String jwt, Long id, String userName, String email, String role) {
        this.id = id;
        this.jwt = jwt;
        this.userName = userName;
        this.role = role;

    }
}
