package com.jayptl08.microservice.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jayptl08.microservice.userservice.model.Credentials;
import com.jayptl08.microservice.userservice.service.CredentialsService;

@RestController
@RequestMapping("/user-service")
public class UserLoginController {

    @Autowired
    private CredentialsService service;

    @PostMapping("/user/login")
    @ResponseStatus(HttpStatus.OK)
    public boolean validateUser(@RequestBody Credentials credentials) {
        return service.validateUser(credentials);
    }

    @PostMapping("/user/login/new")
    @ResponseStatus(HttpStatus.OK)
    public void addNewCredential(@RequestBody Credentials credentials) {
        credentials.setId(null);
        service.addNewCredential(credentials);
    }

    @GetMapping("/user/login")
    @ResponseStatus(HttpStatus.OK)
    public List<Credentials> getAllCredentials() {
        return service.getAllCredentials();
    }

}
