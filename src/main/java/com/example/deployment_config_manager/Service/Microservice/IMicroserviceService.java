package com.example.deployment_config_manager.Service.Microservice;

import com.example.deployment_config_manager.DTO.Microservice.CreateMicroserviceRequest;
import com.example.deployment_config_manager.DTO.Microservice.MicroserviceResponse;
import com.example.deployment_config_manager.DTO.Microservice.UpdateMicroServiceRequest;

import java.util.List;

public interface IMicroserviceService {
    List<MicroserviceResponse> getAllMicroservices();
    MicroserviceResponse getMicroservice(Long id);
    MicroserviceResponse addMicroservice(CreateMicroserviceRequest createMicroserviceRequest);
    MicroserviceResponse updateMicroservice(Long id, UpdateMicroServiceRequest updateMicroServiceRequest);
    void deleteMicroservice(Long id);
}
