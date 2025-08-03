package com.es.practiarmar.controller;

import com.es.practiarmar.dto.employee.EmployeeEditRequestDTO;
import com.es.practiarmar.dto.employee.EmployeeResponseDTO;
import com.es.practiarmar.service.IEmplyeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

    private final IEmplyeeService service;

    @GetMapping("/find/all")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees(){
        List<EmployeeResponseDTO> employees = service.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Void> editEmployee(@PathVariable Long id, EmployeeEditRequestDTO requestDTO){
        service.edit(requestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<EmployeeResponseDTO> findEmployee(@PathVariable Long id){
        EmployeeResponseDTO employee = service.findEmployee(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        service.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
