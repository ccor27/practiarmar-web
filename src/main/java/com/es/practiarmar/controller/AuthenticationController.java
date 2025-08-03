package com.es.practiarmar.controller;

import com.es.practiarmar.dto.auth.AuthenticationRequestDTO;
import com.es.practiarmar.service.IEmplyeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final IEmplyeeService employeeService;

    @GetMapping("/authenticate")
    public ResponseEntity<String> authenticate(AuthenticationRequestDTO requestDTO){
        String token = employeeService.authenticate(requestDTO).token();
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
    //TODO: create the method to save a employee
}
