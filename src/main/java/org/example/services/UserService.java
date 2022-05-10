package org.example.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.models.Users;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.example.repositories.UserRepository;

import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final ReactiveMongoTemplate reactiveMongoTemplate;
    private final UserRepository userRepository;

    public Mono<Users> createUser(Users user) {
        return userRepository.save(user);
    }

    public Flux<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<Users> findById(String userId) {
        return userRepository.findById(userId);
    }

    public Mono<Users> updateUser(String userId, Users user) {
        return userRepository.findById(userId)
                .flatMap(dbUser -> {
                    dbUser.setAge(user.getAge());
                    dbUser.setSalary(user.getSalary());
                    return userRepository.save(dbUser);
                });
    }

    public Mono<Users> deleteUser(String userId) {
        return userRepository.findById(userId)
                .flatMap(existingUser -> userRepository.delete(existingUser)
                        .then(Mono.just(existingUser))
                );
    }

    public Flux<Users> fetchUsers(String name) {
        Query query = new Query()
                .with(Sort.by(Collections.singletonList(Sort.Order.asc("age")))
                );
        query.addCriteria(Criteria
                .where("name")
                .regex(name)
        );
        return reactiveMongoTemplate.find(query, Users.class);
    }
}
