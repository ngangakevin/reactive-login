package org.example.repositories;

import org.example.models.Users;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<Users, String> {
}
