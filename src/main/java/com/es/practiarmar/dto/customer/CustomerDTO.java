package com.es.practiarmar.dto.customer;

public record CustomerDTO(
        Long id,
        String nombre,
        String telefono,
        String email,
        boolean notificaciones
) {
}
