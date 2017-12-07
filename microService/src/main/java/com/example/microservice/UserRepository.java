package com.example.microservice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByLastName(String LastName);

    CompletableFuture<User> findById(Long id);
}
