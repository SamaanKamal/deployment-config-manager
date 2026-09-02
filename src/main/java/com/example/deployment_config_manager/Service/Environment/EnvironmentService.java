package com.example.deployment_config_manager.Service.Environment;

import com.example.deployment_config_manager.DTO.Environment.CreateEnvironmentRequest;
import com.example.deployment_config_manager.DTO.Environment.EnvironmentResponse;
import com.example.deployment_config_manager.DTO.Environment.UpdateEnvironmentRequest;
import com.example.deployment_config_manager.Entity.Environment;
import com.example.deployment_config_manager.Entity.Project;
import com.example.deployment_config_manager.Exception.ResourceNotFoundException;
import com.example.deployment_config_manager.Repository.EnvironmentRepository;
import com.example.deployment_config_manager.Repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class EnvironmentService implements IEnvironmentService {
    private final EnvironmentRepository environmentRepository;
    private final ProjectRepository projectRepository;

    @Override
    public EnvironmentResponse getEnvironment(Long id){
        Environment environment = environmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Environment not found with id: " + id));
        return toResponse(environment);
    }
    @Override
    public List<EnvironmentResponse> getAllEnvironments(){
        List<Environment> environments = environmentRepository.findAll();
        return environments.stream().map(this::toResponse).toList();
    }
    @Override
    public EnvironmentResponse addEnvironment(CreateEnvironmentRequest createEnvironmentRequest){
        Project project = projectRepository.findById(createEnvironmentRequest.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + createEnvironmentRequest.getProjectId()));
        Environment environment = new Environment();
        environment.setName(createEnvironmentRequest.getName());
        environment.setDescription(createEnvironmentRequest.getDescription());
        environment.setProject(project);
        Environment savedEnvironment = environmentRepository.save(environment);
        return toResponse(savedEnvironment);
    }

    @Override
    public EnvironmentResponse updateEnvironment(Long id, UpdateEnvironmentRequest updateEnvironmentRequest){
        return environmentRepository.findById(id).map(environment -> {
            if(updateEnvironmentRequest.getProjectId() != null){
                Project project = projectRepository.findById(updateEnvironmentRequest.getProjectId())
                        .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + updateEnvironmentRequest.getProjectId()));
                environment.setProject(project);
            }
            if(updateEnvironmentRequest.getName() != null){
                environment.setName(updateEnvironmentRequest.getName());
            }
            if(updateEnvironmentRequest.getDescription() != null){
                environment.setDescription(updateEnvironmentRequest.getDescription());
            }
            return toResponse(environmentRepository.save(environment));
        }).orElseThrow(() -> new ResourceNotFoundException("Environment not found with id: " + id));

    }

    @Override
    public void deleteEnvironment(Long id){
        Environment environment = environmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Environment not found with id: " + id));
        environmentRepository.delete(environment);
    }

    private EnvironmentResponse toResponse(Environment environment){
        return  EnvironmentResponse.builder()
                .id(environment.getId())
                .name(environment.getName())
                .projectId(environment.getProject().getId())
                .description(environment.getDescription())
                .createdAt(environment.getCreatedAt())
                .updatedAt(environment.getUpdatedAt())
                .build();
    }
}
