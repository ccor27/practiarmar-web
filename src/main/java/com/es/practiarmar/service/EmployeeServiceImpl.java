package com.es.practiarmar.service;

import com.es.practiarmar.dto.auth.AuthenticationRequestDTO;
import com.es.practiarmar.dto.auth.AuthenticationResponseDTO;
import com.es.practiarmar.dto.employee.EmployeeEditRequestDTO;
import com.es.practiarmar.dto.employee.EmployeeRequestDTO;
import com.es.practiarmar.dto.employee.EmployeeResponseDTO;
import com.es.practiarmar.exception.ResourceNotFoundException;
import com.es.practiarmar.mapper.EmployeeMapper;
import com.es.practiarmar.model.Employee;
import com.es.practiarmar.model.Role;
import com.es.practiarmar.model.Token;
import com.es.practiarmar.model.TokenType;
import com.es.practiarmar.repository.EmployeeRepository;
import com.es.practiarmar.repository.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements IEmplyeeService{

    private final EmployeeRepository repository;
    private final EmployeeMapper employeeMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        return repository.findAll()
                .stream().map(employeeMapper::toDto)
                .toList();
    }
    @Override
    public void save(EmployeeRequestDTO requestDTO) {
        //TODO: make this at the end, I'm not sure if it  is needed
    }

    @Override
    public void edit(EmployeeEditRequestDTO requestDTO) {

        Employee employee = repository.findById(requestDTO.id())
                .orElseThrow(() -> new ResourceNotFoundException("Empleado", "id", requestDTO.id().toString()));
        employeeMapper.updateEntityFromDto(requestDTO, employee);
        employee.setRol(requestDTO.rol());
        repository.save(employee);
    }

    @Override
    public boolean existsEmployee(Long id) {
        return repository.existsById(id);
    }

    @Override
    public EmployeeResponseDTO findEmployee(Long id) {
        return repository.findById(id)
                .map(employeeMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado", "id", "El empleado con id: "+id+" no existe"));
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado", "id", "el empleado con id: "+id+" no existe"));
        employee.setDeleted(true);
        repository.save(employee);
    }

    @Override
    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO authenticationRequestDTO) {
        String username = authenticationRequestDTO.username();
        String password = authenticationRequestDTO.password();
        Employee employee = repository.findByUsernameAndDeletedFalse(username)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado","usuario","El usuario no existe"));
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            password
                    )
            );
            String token = jwtService.generateToken(employee);
            revokeAllEmployeeTokens(employee);
            saveEmployeeToken(employee, token);
            return new AuthenticationResponseDTO(token);
    }
    private void revokeAllEmployeeTokens(Employee employee){
            List<Token> validEmployeeTokens = tokenRepository.findAllValidTokenByCustomer(employee.getId());
            if(validEmployeeTokens.isEmpty())
                return;
            validEmployeeTokens.forEach(t->{
                t.setExpired(true);
                t.setRevoked(true);
            });
            tokenRepository.saveAll(validEmployeeTokens);
    }
    private void saveEmployeeToken(Employee employee, String jwtToken) {
        Token token = new Token(
                null,
                jwtToken,
                TokenType.BEARER,
                employee,
                false,
                false
        );
        tokenRepository.save(token);
    }
}
