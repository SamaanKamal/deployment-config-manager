package com.example.deployment_config_manager.Repository;

import com.example.deployment_config_manager.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
