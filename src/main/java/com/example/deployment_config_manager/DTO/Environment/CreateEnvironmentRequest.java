package com.example.deployment_config_manager.DTO.Environment;

import com.example.deployment_config_manager.Entity.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEnvironmentRequest {
    @NotNull(message = "Project ID is required")
    private Long projectId;
    @NotBlank(message = "Environment name is required")
    @Size(max = 100)
    private String name;
    private String description;
}