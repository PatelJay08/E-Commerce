package com.jayptl08.microservice.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jayptl08.microservice.userservice.model.Credentials;

public interface CredentialsRepository extends JpaRepository<Credentials,String> {

    <List>Credentials findByUserNameAndPassword(String username,String password);

}
