package com.es.practiarmar.dto.item;

public record ItemResponseDTO(
        Long id,
        String name,
        String description,
        int amount,
        double pricePerUnit
) {
}
