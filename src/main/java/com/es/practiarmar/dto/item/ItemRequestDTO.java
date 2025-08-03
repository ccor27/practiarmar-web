package com.es.practiarmar.dto.item;

public record ItemRequestDTO(
        String name,
        String description,
        int amount,
        double pricePerUnit
) {
}
