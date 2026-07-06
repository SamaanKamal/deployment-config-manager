package com.example.deployment_config_manager.DTO.Environment;

import com.example.deployment_config_manager.Entity.Project;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class EnvironmentResponse {
    Long id;
    Long projectId;
    String name;
    String description;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
