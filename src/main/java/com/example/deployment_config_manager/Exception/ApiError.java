package com.example.deployment_config_manager.Exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ApiError(int status,
                       String message,
                       LocalDateTime timestamp,
                       Map<String, String> validationErrors) {
}
