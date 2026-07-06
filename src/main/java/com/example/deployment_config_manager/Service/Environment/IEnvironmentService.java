package com.example.deployment_config_manager.Service.Environment;

import com.example.deployment_config_manager.DTO.Environment.CreateEnvironmentRequest;
import com.example.deployment_config_manager.DTO.Environment.EnvironmentResponse;
import com.example.deployment_config_manager.DTO.Environment.UpdateEnvironmentRequest;
import com.example.deployment_config_manager.DTO.Project.CreateProjectRequest;
import com.example.deployment_config_manager.DTO.Project.ProjectResponse;
import com.example.deployment_config_manager.DTO.Project.UpdateProjectRequest;

import java.util.List;

public interface IEnvironmentService {
    EnvironmentResponse addEnvironment(CreateEnvironmentRequest createEnvironmentRequest);
    EnvironmentResponse getEnvironment(Long id);
    List<EnvironmentResponse> getAllEnvironments();
    EnvironmentResponse updateEnvironment(Long id, UpdateEnvironmentRequest updateEnvironmentRequest);
    void deleteEnvironment(Long environmentId);
}
