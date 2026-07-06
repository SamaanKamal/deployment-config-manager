package com.example.deployment_config_manager.Controller.Project;

import com.example.deployment_config_manager.DTO.Project.CreateProjectRequest;
import com.example.deployment_config_manager.DTO.Project.ProjectResponse;
import com.example.deployment_config_manager.DTO.Project.UpdateProjectRequest;
import com.example.deployment_config_manager.Service.Project.IProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final IProjectService projectService;

    @GetMapping()
    public ResponseEntity<List<ProjectResponse>> getProjects(Long id) {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProject(Long id) {
        return ResponseEntity.ok(projectService.getProject(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ProjectResponse> createProject(CreateProjectRequest createProjectRequest) {
        return ResponseEntity.ok(projectService.addProject(createProjectRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(Long id, UpdateProjectRequest updateProjectRequest) {
        return ResponseEntity.ok(projectService.updateProject(id, updateProjectRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
