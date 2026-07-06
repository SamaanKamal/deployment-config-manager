package com.example.deployment_config_manager.DTO.Microservice;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MicroserviceResponse {
    Long id;
    Long environmentId;
    String name;
    String imageName;
    String chartName;
    String namespace;
    String description;
    String createdAt;
    String updatedAt;
}
