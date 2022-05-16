package org.example.repositories;

import org.example.models.Users;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveCrudRepository<Users, String> {

    @Query("{'name': ?0}")
    Flux<Users> findByName(String name);
}
