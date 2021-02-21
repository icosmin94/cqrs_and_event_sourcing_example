package com.springbank.oauth2_server.repositories;

import cqrs.event.sourcing.example.usercore.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
