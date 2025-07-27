package com.es.practiarmar.mapper;

import com.es.practiarmar.dto.employee.EmployeeEditRequestDTO;
import com.es.practiarmar.dto.employee.EmployeeRequestDTO;
import com.es.practiarmar.dto.employee.EmployeeResponseDTO;
import com.es.practiarmar.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "codigo", ignore = true) // El código se genera en el servicio
    @Mapping(target = "isDeleted", ignore = true)
    @Mapping(target = "loginAttempts", ignore = true)
    @Mapping(target = "accountLocked", ignore = true)
    @Mapping(target = "lockUntil", ignore = true)
    @Mapping(target = "contrasena", ignore = true) // La contraseña se encripta en el servicio
    Employee toEntity(EmployeeRequestDTO dto);

    EmployeeResponseDTO toDto(Employee entity);

    @Mapping(target = "id", ignore = true) // El ID no se actualiza desde el DTO, se usa del path
    @Mapping(target = "codigo", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    @Mapping(target = "loginAttempts", ignore = true)
    @Mapping(target = "accountLocked", ignore = true)
    @Mapping(target = "lockUntil", ignore = true)
    @Mapping(target = "contrasena", ignore = true) // La contraseña se cambia con un método específico
    void updateEntityFromDto(EmployeeEditRequestDTO dto, @MappingTarget Employee employee);
}
