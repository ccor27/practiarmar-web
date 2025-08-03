package com.es.practiarmar.service;

import com.es.practiarmar.dto.auth.AuthenticationRequestDTO;
import com.es.practiarmar.dto.auth.AuthenticationResponseDTO;
import com.es.practiarmar.dto.employee.EmployeeEditRequestDTO;
import com.es.practiarmar.dto.employee.EmployeeRequestDTO;
import com.es.practiarmar.dto.employee.EmployeeResponseDTO;
import com.es.practiarmar.model.Role;

import java.util.List;

public interface IEmplyeeService {
    List<EmployeeResponseDTO> getAllEmployees();
    void save(EmployeeRequestDTO requestDTO);
    void edit(EmployeeEditRequestDTO requestDTO);
    boolean existsEmployee(Long id);
    EmployeeResponseDTO findEmployee(Long id);
    void deleteEmployee(Long id);
    AuthenticationResponseDTO authenticate(AuthenticationRequestDTO authenticationRequestDTO);
}
