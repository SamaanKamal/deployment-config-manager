package com.example.deployment_config_manager.Service.Microservice;

import com.example.deployment_config_manager.DTO.Microservice.CreateMicroserviceRequest;
import com.example.deployment_config_manager.DTO.Microservice.MicroserviceResponse;
import com.example.deployment_config_manager.DTO.Microservice.UpdateMicroserviceRequest;
import com.example.deployment_config_manager.Entity.Environment;
import com.example.deployment_config_manager.Entity.Microservice;
import com.example.deployment_config_manager.Repository.EnvironmentRepository;
import com.example.deployment_config_manager.Repository.MicroserviceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class MicroserviceService implements IMicroserviceService {
    private final MicroserviceRepository microserviceRepository;
    private final EnvironmentRepository environmentRepository;
    @Override
    public List<MicroserviceResponse> getAllMicroservices() {
        List<Microservice> microservices = microserviceRepository.findAll();
        return microservices.stream().map(this::toResponse).toList();
    }

    @Override
    public MicroserviceResponse getMicroservice(Long id) {
        return microserviceRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Microservice not found with id: " + id));
    }

    @Override
    public MicroserviceResponse addMicroservice(CreateMicroserviceRequest createMicroserviceRequest) {
        Environment environment = environmentRepository.findById(createMicroserviceRequest.getEnvironmentId())
                .orElseThrow(() -> new RuntimeException("Environment not found with id: " + createMicroserviceRequest.getEnvironmentId()));
        Microservice microservice = new Microservice();
        microservice.setName(createMicroserviceRequest.getName());
        microservice.setImageName(createMicroserviceRequest.getImageName());
        microservice.setChartName(createMicroserviceRequest.getChartName());
        microservice.setNamespace(createMicroserviceRequest.getNamespace());
        microservice.setDescription(createMicroserviceRequest.getDescription());
        microservice.setEnvironment(environment);
        return toResponse(microserviceRepository.save(microservice));
    }

    @Override
    public MicroserviceResponse updateMicroservice(Long id, UpdateMicroserviceRequest updateMicroServiceRequest) {
        return microserviceRepository.findById(id)
                .map(microservice -> {
                    if(updateMicroServiceRequest.getEnvironmentId() != null) {
                        Environment environment = environmentRepository.findById(updateMicroServiceRequest.getEnvironmentId())
                                .orElseThrow(() -> new RuntimeException("Environment not found with id: " + updateMicroServiceRequest.getEnvironmentId()));
                        microservice.setEnvironment(environment);
                    }
                    microservice.setName(updateMicroServiceRequest.getName());
                    microservice.setImageName(updateMicroServiceRequest.getImageName());
                    microservice.setChartName(updateMicroServiceRequest.getChartName());
                    microservice.setNamespace(updateMicroServiceRequest.getNamespace());
                    microservice.setDescription(updateMicroServiceRequest.getDescription());
                    return toResponse(microserviceRepository.save(microservice));
                })
                .orElseThrow(() -> new RuntimeException("Microservice not found with id: " + id));
    }

    @Override
    public void deleteMicroservice(Long id) {
        Microservice microservice = microserviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Microservice not found with id: " + id));
        microserviceRepository.delete(microservice);
    }

    private MicroserviceResponse toResponse(Microservice microservice) {
        return MicroserviceResponse.builder()
                .id(microservice.getId())
                .environmentId(microservice.getEnvironment().getId())
                .name(microservice.getName())
                .imageName(microservice.getImageName())
                .chartName(microservice.getChartName())
                .namespace(microservice.getNamespace())
                .description(microservice.getDescription())
                .createdAt(microservice.getCreatedAt())
                .updatedAt(microservice.getUpdatedAt())
                .build();
    }
}
