package com.example.deployment_config_manager.DTO.Microservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMicroServiceRequest {
    private Long microserviceId;
    private String name;
    private String imageName;
    private String chartName;
    private String namespace;
    private String description;
}
