package com.example.deployment_config_manager.DTO.Project;

import lombok.*;

import java.time.LocalDateTime;

@Value
@Builder
public class ProjectResponse {
    Long id;
    String name;
    String description;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
