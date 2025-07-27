package com.es.practiarmar.dto.customer;

import jakarta.validation.constraints.*;

public record CustomerRequestDTO(
        @NotBlank(message = "El nombre no puede estar vacío.")
        @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres.")
        String nombre,

        @NotBlank(message = "El teléfono no puede estar vacío.")
        @Size(min = 10, max = 20, message = "El teléfono debe tener entre 10 y 20 dígitos.")
        @Pattern(regexp = "^[0-9]+$", message = "El teléfono solo puede contener números.")
        String telefono,

        @NotBlank(message = "El email no puede estar vacío.")
        @Email(message = "Formato de email inválido.")
        @Size(max = 100, message = "El email no puede exceder los 100 caracteres.")
        String email,

        @NotNull(message = "Se debe especificar si el cliente recibe notificaciones.")
        Boolean recibirNotificaciones
) {
}
