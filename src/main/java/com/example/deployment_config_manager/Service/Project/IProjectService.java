package com.example.deployment_config_manager.Service.Project;

import com.example.deployment_config_manager.DTO.Project.CreateProjectRequest;
import com.example.deployment_config_manager.DTO.Project.ProjectResponse;
import com.example.deployment_config_manager.DTO.Project.UpdateProjectRequest;

import java.util.List;

public interface IProjectService {
    ProjectResponse addProject(CreateProjectRequest createProjectRequest);
    ProjectResponse getProject(Long id);
    List<ProjectResponse> getAllProjects();
    ProjectResponse updateProject(Long id, UpdateProjectRequest updateProjectRequest);
    void deleteProject(Long id);

}
