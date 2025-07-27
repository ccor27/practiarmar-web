package com.es.practiarmar.dto.employee;

import java.util.List;

public record EmployeeResponseDTO(
        Long id,
        String name,
        String telefono,
        String email,
        String codigo,
        String username,
        String rol,
        List<EmployeePaymentResponseDTO> pagos
) {
}
