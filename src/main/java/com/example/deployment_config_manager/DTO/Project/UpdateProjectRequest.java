package com.example.deployment_config_manager.DTO.Project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProjectRequest {
    private String name;
    private String description;
}
