package com.es.practiarmar.service;

import com.es.practiarmar.dto.employee.EmployeeEditRequestDTO;
import com.es.practiarmar.dto.employee.EmployeeRequestDTO;
import com.es.practiarmar.dto.employee.EmployeeResponseDTO;
import com.es.practiarmar.mapper.EmployeeMapper;
import com.es.practiarmar.model.Role;
import com.es.practiarmar.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements IEmplyeeService{

    private final EmployeeRepository repository;
    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        return repository.findAll()
                .stream().map(employeeMapper::toDto)
                .toList();
    }
    @Override
    public void save(EmployeeRequestDTO requestDTO) {

    }

    @Override
    public void edit(EmployeeEditRequestDTO requestDTO, Role rol) {

    }

    @Override
    public boolean existsEmployee(Long id) {
        return false;
    }

    @Override
    public EmployeeResponseDTO findEmployee(Long id) {
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {

    }
}
