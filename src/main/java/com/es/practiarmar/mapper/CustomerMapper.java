package com.es.practiarmar.mapper;

import com.es.practiarmar.dto.customer.CustomerRequestDTO;
import com.es.practiarmar.dto.customer.CustomerDTO;
import com.es.practiarmar.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.ReportingPolicy.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface CustomerMapper {

    // Método para convertir de CustomerRequestDTO (lo que recibes) a Customer (la entidad)
    // @Mapping: Se usa cuando los nombres de los campos son diferentes o si necesitas ignorar un campo.
    //           Aquí, ignoramos 'id' y 'isDeleted' porque no vienen del DTO y se gestionan en el servicio.
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    Customer toEntity(CustomerRequestDTO dto);

    // Método para convertir de Customer (la entidad) a CustomerResponseDTO (lo que devuelves)
    CustomerDTO toDto(Customer entity);

    // Opcional: un método para actualizar una entidad existente a partir de un DTO
    // @MappingTarget: Indica que 'customer' es el objeto que debe ser actualizado.
    //                 Los campos no ignorados en el DTO se usarán para actualizar el customer.
    // @Mapping(target = "id", ignore = true): El ID nunca se actualiza desde el DTO.
    // @Mapping(target = "isDeleted", ignore = true): 'isDeleted' no se actualiza desde el DTO.
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    void updateEntityFromDto(CustomerDTO dto, @MappingTarget
    Customer customer);
}

