package com.example.deployment_config_manager.DTO.Environment;

import com.example.deployment_config_manager.Entity.Project;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEnvironmentRequest {
    private Long projectId;
    private String name;
    private String description;
}