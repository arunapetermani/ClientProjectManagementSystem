package com.example.demo.client.dto;

import com.example.demo.project.dto.ProjectMapping;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDetails {
    @Id
    @NotBlank(message = "ClientId should be entered")
    private String clientId;
//    @OneToMany(targetEntity= ProjectMapping.class,
//            fetch = FetchType.LAZY, orphanRemoval = true)
//    List<ProjectMapping> projectMapping;
    @NotNull()
    @NotBlank(message = "ClientName should be entered")
    private String clientName;

    @NotEmpty
    @Email
    @Size(min = 8,max = 50)
    @NotBlank(message = "Email Id should be entered")
    private String clientEmail;
    private String fileName;
    private String date;
    @Lob
    private byte[] data;

}
