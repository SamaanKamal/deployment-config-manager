package com.example.deployment_config_manager.Repository;

import com.example.deployment_config_manager.Entity.Microservice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MicroserviceRepository extends JpaRepository<Microservice, Long> {
}
