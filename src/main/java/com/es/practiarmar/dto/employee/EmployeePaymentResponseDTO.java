package com.es.practiarmar.dto.employee;

public record EmployeePaymentResponseDTO(
        Long id,
        String codigoProyecto,
        double total,
        String estado
) {
}
