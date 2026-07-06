package com.example.deployment_config_manager.Service.Project;

import com.example.deployment_config_manager.DTO.Project.CreateProjectRequest;
import com.example.deployment_config_manager.DTO.Project.ProjectResponse;
import com.example.deployment_config_manager.DTO.Project.UpdateProjectRequest;
import com.example.deployment_config_manager.Entity.Project;
import com.example.deployment_config_manager.Repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ProjectService implements IProjectService{

    private final ProjectRepository projectRepository;

    @Override
    public ProjectResponse addProject(CreateProjectRequest createProjectRequest) {
        Project project = new Project();
        project.setName(createProjectRequest.getName());
        project.setDescription(createProjectRequest.getDescription());
        return toResponse(projectRepository.save(project));
    }

    @Override
    public ProjectResponse getProject(Long id) {
        return projectRepository.findById(id).map(this::toResponse).orElseThrow(() -> new RuntimeException("this project doesn't exist"));
    }

    @Override
    public List<ProjectResponse> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(this::toResponse).toList();
    }

    @Override
    public ProjectResponse updateProject(Long id, UpdateProjectRequest updateProjectRequest) {
        return projectRepository.findById(id).map(project -> {
            project.setName(updateProjectRequest.getName());
            project.setDescription(updateProjectRequest.getDescription());
            return toResponse(projectRepository.save(project));
        }).orElseThrow(() -> new RuntimeException("this project doesn't exist"));
    }

    @Override
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("this project doesn't exist"));
        projectRepository.delete(project);
    }

    private ProjectResponse toResponse(Project project){
        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .createdAt(project.getCreatedAt())
                .updatedAt(project.getUpdatedAt())
                .build();
    }
}
