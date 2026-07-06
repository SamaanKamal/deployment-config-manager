package com.example.deployment_config_manager.Service.Environment;

import com.example.deployment_config_manager.DTO.Environment.CreateEnvironmentRequest;
import com.example.deployment_config_manager.DTO.Environment.EnvironmentResponse;
import com.example.deployment_config_manager.DTO.Environment.UpdateEnvironmentRequest;
import com.example.deployment_config_manager.Entity.Environment;
import com.example.deployment_config_manager.Repository.EnvironmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class EnvironmentService implements IEnvironmentService {
    private final EnvironmentRepository environmentRepository;

    @Override
    public EnvironmentResponse getEnvironment(Long id){
        Environment environment = environmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Environment not found with id: " + id));
        return toResponse(environment);
    }
    @Override
    public List<EnvironmentResponse> getAllEnvironments(){
        List<Environment> environments = environmentRepository.findAll();
        return environments.stream().map(this::toResponse).toList();
    }
    @Override
    public EnvironmentResponse addEnvironment(CreateEnvironmentRequest createEnvironmentRequest){
        Environment environment = new Environment();
        environment.setName(createEnvironmentRequest.getName());
        environment.setDescription(createEnvironmentRequest.getDescription());
        Environment savedEnvironment = environmentRepository.save(environment);
        return toResponse(savedEnvironment);
    }

    @Override
    public EnvironmentResponse updateEnvironment(Long id, UpdateEnvironmentRequest updateEnvironmentRequest){
        return environmentRepository.findById(id).map(environment -> {
            environment.setName(updateEnvironmentRequest.getName());
            environment.setDescription(updateEnvironmentRequest.getDescription());
            return toResponse(environmentRepository.save(environment));
        }).orElseThrow(() -> new RuntimeException("Environment not found with id: " + id));

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
