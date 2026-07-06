package com.example.deployment_config_manager.Repository;

import com.example.deployment_config_manager.Entity.Environment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnvironmentRepository extends JpaRepository<Environment, Long> {
}
