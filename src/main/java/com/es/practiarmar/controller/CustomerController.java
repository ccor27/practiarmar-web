package com.es.practiarmar.controller;

import com.es.practiarmar.dto.customer.CustomerDTO;
import com.es.practiarmar.dto.customer.CustomerRequestDTO;
import com.es.practiarmar.service.ICustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    private final ICustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<Void> createCustomer(@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {
        customerService.save(customerRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<Void> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        customerService.edit(customerDTO);
        return new ResponseEntity<>(HttpStatus.OK); // Devuelve un 200 OK (éxito al actualizar)
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Devuelve un 204 No Content (éxito sin cuerpo de respuesta)
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK); // Devuelve un 200 OK con la lista de clientes
    }

    @GetMapping("/find/{id}") // Mapea las solicitudes GET a /api/customers/{id}
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        CustomerDTO customer = customerService.findById(id); // Llama al servicio para encontrar por ID
        return new ResponseEntity<>(customer, HttpStatus.OK); // Devuelve un 200 OK con el cliente encontrado
    }

    @GetMapping("/emails-notifications")
    public ResponseEntity<List<String>> getAllCustomersEmailsForNotifications() {
        List<String> emails = customerService.findAllCustomersEmails();
        return new ResponseEntity<>(emails, HttpStatus.OK); // Devuelve un 200 OK con la lista de emails
    }
}
