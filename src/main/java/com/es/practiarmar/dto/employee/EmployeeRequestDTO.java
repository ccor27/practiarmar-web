package com.es.practiarmar.dto.employee;

import com.es.practiarmar.model.Role;
import jakarta.validation.constraints.*;

public record EmployeeRequestDTO(
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

        @NotBlank(message = "El nombre de usuario no puede estar vacío.")
        @Size(min = 4, max = 50, message = "El nombre de usuario debe tener entre 4 y 50 caracteres.")
        String username,

        @NotBlank(message = "La contraseña no puede estar vacía.")
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres.")
        String contrasena,

        @NotNull(message = "El rol no puede ser nulo.")
        Role rol // Usar el enum directamente
) {
}
