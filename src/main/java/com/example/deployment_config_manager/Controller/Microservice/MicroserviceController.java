package com.example.deployment_config_manager.Controller.Microservice;

import com.example.deployment_config_manager.DTO.Microservice.CreateMicroserviceRequest;
import com.example.deployment_config_manager.DTO.Microservice.MicroserviceResponse;
import com.example.deployment_config_manager.DTO.Microservice.UpdateMicroServiceRequest;
import com.example.deployment_config_manager.Service.Microservice.IMicroserviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/microservices")
public class MicroserviceController {
    private final IMicroserviceService microserviceService;

    @GetMapping()
    public ResponseEntity<List<MicroserviceResponse>> getMicroservices(Long id) {
        return ResponseEntity.ok(microserviceService.getAllMicroservices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MicroserviceResponse> getMicroservice(Long id) {
        return ResponseEntity.ok(microserviceService.getMicroservice(id));
    }

    @PostMapping("/create")
    public ResponseEntity<MicroserviceResponse> createMicroservice(CreateMicroserviceRequest createMicroserviceRequest) {
        return ResponseEntity.ok(microserviceService.addMicroservice(createMicroserviceRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MicroserviceResponse> updateMicroservice(Long id, UpdateMicroServiceRequest updateMicroServiceRequest) {
        return ResponseEntity.ok(microserviceService.updateMicroservice(id, updateMicroServiceRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMicroService(Long id) {
        microserviceService.deleteMicroservice(id);
        return ResponseEntity.noContent().build();
    }
}
