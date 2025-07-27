package com.es.practiarmar.service;

import com.es.practiarmar.dto.customer.CustomerRequestDTO;
import com.es.practiarmar.dto.customer.CustomerDTO;

import java.util.List;

public interface ICustomerService {
    void save(CustomerRequestDTO customerRequestDTO);
    void edit(CustomerDTO customerDTO);
    void delete(Long id);
    List<CustomerDTO> findAll();//TODO: it is needed to return a DTO instead of the entity
    CustomerDTO findById(Long id);
    List<String> findAllCustomersEmails();
}
