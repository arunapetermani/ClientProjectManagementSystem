package com.example.demo.project.dto;

import com.example.demo.client.dto.ClientDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectMapping {
    @Id
    @NotBlank(message = "Enter the ProjectId")
    @NotNull
    private String projectId;

    //private String clientId;

    @NotBlank(message = "Enter the Status field")
    private String projectStatus;

    private String date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clientId", nullable = false)
    ClientDetails clientDetails;
}
