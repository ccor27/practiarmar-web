package com.es.practiarmar.dto.project;

import com.es.practiarmar.dto.item.ItemResponseDTO;
import com.es.practiarmar.dto.picture.PictureResponseDTO;
import com.es.practiarmar.model.ProjectStatus;

import java.util.List;

public record ProjectResponseDTO(
        Long id,
        String name,
        String code,
        String customer,
        double materialPrice,
        double employeePrice,
        double laborPrice,
        double totalPrice,
        String deliveryDate,
        String modificationDate,
        String description,
        List<String> employees,
        List<PictureResponseDTO> pictures,
        List<ItemResponseDTO> items,
        ProjectStatus projectStatus,
        boolean isDeleted
) {
}
