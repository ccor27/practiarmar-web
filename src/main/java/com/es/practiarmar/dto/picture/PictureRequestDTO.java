package com.es.practiarmar.dto.picture;

import java.util.Date;

public record PictureRequestDTO(
        String name,
        String type,
        Long size,
        Date date,
        byte[] image
) {
}
