package com.springbank.oauth2_server.repositories;

import cqrs.event.sourcing.example.usercore.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{'account:username': ?0}")
    Optional<User> findByUsername(String username);
}
