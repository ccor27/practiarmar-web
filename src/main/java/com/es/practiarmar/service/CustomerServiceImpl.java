package com.es.practiarmar.service;

import com.es.practiarmar.exception.DuplicateEntryException;
import com.es.practiarmar.exception.ResourceNotFoundException;
import com.es.practiarmar.mapper.CustomerMapper;
import com.es.practiarmar.model.Customer;
import com.es.practiarmar.dto.customer.CustomerRequestDTO;
import com.es.practiarmar.dto.customer.CustomerDTO;
import com.es.practiarmar.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService{

    private final CustomerRepository repository;
    private final CustomerMapper customerMapper;

    @Override
    public void save(CustomerRequestDTO customerRequestDTO) {
        if(repository.existsByNombreAndDeletedFalse(customerRequestDTO.nombre())) {
            throw new DuplicateEntryException("Cliente", "nombre", customerRequestDTO.nombre());
        }
        if (customerRequestDTO.email() != null && repository.existsByEmailAndIsDeletedFalse(customerRequestDTO.email())) {
            throw new DuplicateEntryException("Cliente", "email", customerRequestDTO.email());
        }
        Customer customer = customerMapper.toEntity(customerRequestDTO);
        customer.setDeleted(false);

        repository.save(customer);
    }

    @Override
    public void edit(CustomerDTO customerDTO) {
        if(repository.existsByNombreAndIdNot(customerDTO.nombre(), customerDTO.id()))
            throw  new DuplicateEntryException("Cliente", "nombre", customerDTO.nombre());

        Customer customer = repository.findByIdAndDeletedFalse(customerDTO.id()).orElseThrow(
                () -> new ResourceNotFoundException("Cliente", "id", String.valueOf(customerDTO.id()))
        );
        customerMapper.updateEntityFromDto(customerDTO, customer);
    }

    @Override
    public void delete(Long id) {
        Customer customer = repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", String.valueOf(id)));
        customer.setDeleted(true);
        repository.save(customer);
    }

    @Override
    public List<CustomerDTO> findAll() {
        return repository.findAllByDeletedIsFalse()
                .stream()
                .map(customerMapper::toDto)
                .toList();
    }

    @Override
    public CustomerDTO findById(Long id) {
        return repository.findByIdAndDeletedFalse(id)
                .map(customerMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", String.valueOf(id)));
    }

    @Override
    public List<String> findAllCustomersEmails() {
        return repository.fetchEmailCustomersNotification()
                .stream().map(String::toLowerCase)
                .toList();
    }
}
