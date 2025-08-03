package com.es.practiarmar.dto.project;

import com.es.practiarmar.dto.employee.EmployeeResponseDTO;
import com.es.practiarmar.dto.item.ItemRequestDTO;
import com.es.practiarmar.dto.picture.PictureRequestDTO;
import com.es.practiarmar.model.Customer;

import java.time.LocalDate;
import java.util.List;

public record ProjectRequestDTO(
        String projectName,
        Customer customer,
        double materialPrice,
        double employeePrice,
        double laborPrice,
        double totalPrice,
        LocalDate deliveryDate,
        String description,
        List<ItemRequestDTO>items,
        List<EmployeeResponseDTO> employees,
        List<PictureRequestDTO> pictures
){
}
