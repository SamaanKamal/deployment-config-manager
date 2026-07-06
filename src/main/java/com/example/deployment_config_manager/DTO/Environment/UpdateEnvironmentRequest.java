package com.example.deployment_config_manager.DTO.Environment;

import com.example.deployment_config_manager.Entity.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEnvironmentRequest {
    private String name;
    private String description;
}
