package com.jayptl08.microservice.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayptl08.microservice.userservice.model.Credentials;
import com.jayptl08.microservice.userservice.repository.CredentialsRepository;

@Service
public class CredentialsService {

    @Autowired
    private CredentialsRepository repository;

    public boolean validateUser(Credentials credentials) {
        if (repository.findByUserNameAndPassword(credentials.getUserName(), credentials.getPassword())==null) {
            return false;
        }
        return true;
    }

    public List<Credentials> getAllCredentials(){
        return repository.findAll();
    }

    public void addNewCredential(Credentials credentials){
        repository.save(credentials);
    }

}
