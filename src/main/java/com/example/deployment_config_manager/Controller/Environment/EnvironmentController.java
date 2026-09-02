package com.example.deployment_config_manager.Controller.Environment;

import com.example.deployment_config_manager.DTO.Environment.CreateEnvironmentRequest;
import com.example.deployment_config_manager.DTO.Environment.EnvironmentResponse;
import com.example.deployment_config_manager.DTO.Environment.UpdateEnvironmentRequest;
import com.example.deployment_config_manager.Service.Environment.IEnvironmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/environments")
@RequiredArgsConstructor
public class EnvironmentController {
    private final IEnvironmentService environmentService;

    @GetMapping
    public ResponseEntity<List<EnvironmentResponse>> getAllEnvironments() {
        return ResponseEntity.ok(environmentService.getAllEnvironments());
    }
    @GetMapping("/{id}")
    public ResponseEntity<EnvironmentResponse> getEnvironment(@PathVariable("id") Long id) {
        return ResponseEntity.ok(environmentService.getEnvironment(id));
    }
    @PostMapping()
    public ResponseEntity<EnvironmentResponse> createEnvironment(@Valid @RequestBody CreateEnvironmentRequest createEnvironmentRequest) {
        return ResponseEntity.ok(environmentService.addEnvironment(createEnvironmentRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<EnvironmentResponse> updateEnvironment(@PathVariable("id") Long id, @RequestBody UpdateEnvironmentRequest updateEnvironmentRequest) {
        return ResponseEntity.ok(environmentService.updateEnvironment(id, updateEnvironmentRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnvironment(@PathVariable("id") Long id) {
        environmentService.deleteEnvironment(id);
        return ResponseEntity.noContent().build();
    }
}
