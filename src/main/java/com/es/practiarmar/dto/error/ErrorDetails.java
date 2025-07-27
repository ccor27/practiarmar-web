package com.es.practiarmar.dto.error;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorDetails(
        LocalDateTime timestamp,
        String message,
        String details,
        Map<String, String> fieldErrors
) {
    public ErrorDetails(LocalDateTime timestamp, String message, String details) {
        this(timestamp, message, details, null);
    }
}
