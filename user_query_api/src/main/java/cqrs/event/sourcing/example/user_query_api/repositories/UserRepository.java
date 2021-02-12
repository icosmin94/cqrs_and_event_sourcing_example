package cqrs.event.sourcing.example.user_query_api.repositories;

import cqrs.event.sourcing.example.usercore.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
